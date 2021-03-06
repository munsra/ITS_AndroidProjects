package com.example.piero.mysqlandroid1;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by piero on 18/05/2016.
 */
public class BackgroundWorker extends AsyncTask<String, String, String>{

    Context context;
    AlertDialog alertDialog;
    BackgroundWorker (Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String url_login = "http://pierosilvestri1.altervista.org/mysql-postnote1/postnote1-registration.php";
        String url_register = "http://pierosilvestri1.altervista.org/mysql-postnote1/postnote1-registration.php";

        if(type.equals("register")){

            try {
                String titolo = params[1];
                String testo = params[2];
                String data = params[3];
                String audio  = params[4];
                String immagine = params[5];
                URL url = new URL(url_register);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("titolo", "UTF-8")+ "=" + URLEncoder.encode(titolo, "UTF-8") + "&"
                        + URLEncoder.encode("testo", "UTF-8")+ "=" + URLEncoder.encode(testo, "UTF-8") + "&"
                        + URLEncoder.encode("data", "UTF-8")+ "=" + URLEncoder.encode(data, "UTF-8") + "&"
                        + URLEncoder.encode("audio", "UTF-8")+ "=" + URLEncoder.encode(audio, "UTF-8") + "&"
                        + URLEncoder.encode("immagine", "UTF-8") + "=" + URLEncoder.encode(immagine, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }
            catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute(){
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
