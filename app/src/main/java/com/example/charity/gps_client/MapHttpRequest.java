package com.example.charity.gps_client;

import android.app.Activity;
import android.content.Context;
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
public class MapHttpRequest {
    String url = "https://google.com";
    String id = "bus_id";
    Context context;

    public MapHttpRequest(Activity activity){
        context = activity;
    }

    public void sendGPS(LatLng latLng){
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        HttpResponse httpResponse;
        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("lat", String.valueOf(latLng.latitude)));
        params.add(new BasicNameValuePair("lng", String.valueOf(latLng.longitude)));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            httpResponse = httpClient.execute(httpPost);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT);
        }
    }
}
