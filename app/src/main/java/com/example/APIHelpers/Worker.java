package com.example.APIHelpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;

public class Worker<T>{
    public Class<T> cl;
    public Gson gson;
    public GetHelper get;
    public PostHelper post;
//    public Context context;

    public Worker(){}

    public Worker(Class<T> cl/*, Context context*/){
        this.cl=cl;
        gson=new Gson();
        get=new GetHelper();
        post=new PostHelper();
//        this.context=context;
    }

    public void setClass(Class<T> cl){
        this.cl=cl;
    }

//    private static <T> T cast(Object o) {
//        return (T) o;
//    }

    public class GetHelper extends AsyncTask<String, Void, List<T>>{
        @Override
        protected List<T> doInBackground(String... strings) {
            String rs="";

            try {
                URL url=new URL(strings[0]);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line=null;
                StringBuilder sb=new StringBuilder("");

                while((line=br.readLine())!=null){
                    sb.append(line);
                }

                rs=sb.toString();

//                Log.i("api response: ", rs);

                Type listType= TypeToken.getParameterized(ArrayList.class, cl).getType();

//                    return rs;
                return new Gson().fromJson(rs, listType);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public class PostHelper extends AsyncTask<String, Void, Object> {
        Object object;
//        ProgressDialog progressDialog=new ProgressDialog(context);

        public PostHelper(){}

        public PostHelper(Object obj){
            this.object=obj;
        }

        public void setObject(Object obj){
            object=obj;
        }

//        @Override
//        protected void onPreExecute(){
//            progressDialog.setCancelable(false);
//            progressDialog.show();
//        }

        //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Object doInBackground(String... strings) {
            String _url=strings[0];
            String jsonStr=gson.toJson(object);
            int temp=0;

            try {
                URL url=new URL(_url);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();

                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
                con.connect();

//                JSONObject tmp=new JSONObject();
//                tmp.put("email", "abc@gmail.com");
//                tmp.put("name", "new user");
//                tmp.put("active", true);
//                String jsonString="{\"email\": \"luyen@gmail.com\", \"name\": \"Lê Văn Luyện\", \"active\": true}";
                OutputStreamWriter os=new OutputStreamWriter(con.getOutputStream());
                //byte[] input=jsonString.getBytes(StandardCharsets.UTF_8);
                //os.write(tmp.toString().getBytes("UTF-8"));
                os.write(jsonStr /*jsonString*/);
                os.flush();
                os.close();

//                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//                wr.writeBytes(tmp.toString());
//                wr.flush();
//                wr.close();

                try(BufferedReader br=new BufferedReader(
                        new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))){
                        StringBuilder sb=new StringBuilder();
                        String response=null;
                        while((response=br.readLine())!=null){
                            sb.append(response.trim());
                        }
                    //Log.i("api response: ", sb.toString());
//                        Type listType= TypeToken.getParameterized(ArrayList.class, cl).getType();
//                    String _response=sb.toString();
                    Object rs=gson.fromJson(sb.toString(), cl);

                    return rs;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

//        @Override
//        protected void onPostExecute(Object result){
//            progressDialog.dismiss();
//        }
    }
}
