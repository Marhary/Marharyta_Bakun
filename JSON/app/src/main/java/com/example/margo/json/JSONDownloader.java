package com.example.margo.json;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class JSONDownloader extends AsyncTask<Void,Void,String> {

    Context c;
    String jsonURL;
    Spinner sp;
    ProgressDialog pd;

    public JSONDownloader(String jsonURL, Context c, Spinner sp) {
        this.jsonURL = jsonURL;
        this.c =c;
        this.sp = sp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Download JSON");
        pd.setMessage("Downloading...");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.download();
    }

    @Override
    protected void onPostExecute(String jsonData) {

        super.onPostExecute(jsonData);

        pd.dismiss();
        if (jsonData.startsWith("Error")) {
            Toast.makeText(c, jsonData, Toast.LENGTH_SHORT).show();
        } else {
            new JSONParser(c, jsonData, sp).execute();
        }
    }

    private String download() {
        Object connection = Connector.connect(jsonURL);
        if (connection.toString().startsWith("Error")) {
            return connection.toString();
        }
        try {
            HttpURLConnection con = (HttpURLConnection) connection;
            if (con.getResponseCode() == con.HTTP_OK) {
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line;
                StringBuilder jsonData = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    jsonData.append(line).append("\n");
                }

                br.close();
                is.close();
                return jsonData.toString();

            } else {
                return "Error " + con.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();
        }
    }
}
