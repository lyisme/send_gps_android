package com.example.charity.gps_client;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by charity on 2014-09-27.
 */
public class MapHttpRequest extends AsyncTask<LatLng, Void, Void> {
    String host = "http://bustracking.apiary-mock.com";
    String api = "/send_gps/";
    String id = "bus_id";

    @Override
    protected Void doInBackground(LatLng... args) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(host+api+id);

        LatLng latLng = (LatLng)args[0];
        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("lat", String.valueOf(latLng.latitude)));
        params.add(new BasicNameValuePair("lng", String.valueOf(latLng.longitude)));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            Log.d("response", httpResponse.toString());

        } catch (Exception e) {
            e.printStackTrace();
        };
        return null;
    }
}
