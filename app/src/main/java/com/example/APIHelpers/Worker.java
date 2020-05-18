package com.example.APIHelpers;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import androidx.annotation.RequiresApi;

public class Worker {
    public PostWorker postWorker;
    public GetWorker getWorker;

    public Worker() {
        postWorker = new PostWorker();
        getWorker = new GetWorker();
    }

    public class PostWorker extends AsyncTask<String, Void, String> {

        //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... strings) {
            String _url = strings[0];
            String jsonStr = strings[1];

            try {
                URL url = new URL(_url);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
                con.connect();

//                JSONObject tmp = new JSONObject();
//                tmp.put("email", "abc@gmail.com");
//                tmp.put("name", "new user");
//                tmp.put("active", true);
//                String jsonString = "{\"email\": \"luyen@gmail.com\", \"name\": \"Lê Văn Luyện\", \"active\": true}";
                OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream());
                BufferedWriter wr = new BufferedWriter(os);
                //byte[] input=jsonString.getBytes(StandardCharsets.UTF_8);
                //os.write(tmp.toString().getBytes("UTF-8"));
                wr.write(jsonStr);
                wr.flush();
                wr.close();

//                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//                wr.writeBytes(tmp.toString());
//                wr.flush();
//                wr.close();

                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder sb = new StringBuilder();
                    String response = null;
                    while ((response = br.readLine()) != null) {
                        sb.append(response.trim());
                    }
                    Log.i("api response: ", sb.toString());

                    return sb.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";
        }
    }

    public class GetWorker extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String response = "";

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = null;
                StringBuilder sb = new StringBuilder("");

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                response = sb.toString();

                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }
    }
}
