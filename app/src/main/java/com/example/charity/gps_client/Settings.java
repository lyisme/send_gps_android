package com.example.charity.gps_client;

import java.util.HashMap;

/**
 * Created by mysquar on 10/11/14.
 */
public interface Settings {
    String HOST_NAME="http://192.168.1.109:8080";
    int TIME_UPDATE=5000;
    int ZOOM_LEVEL = 15;

    HashMap<String, String> APIS = new HashMap<String, String>(){{
        put("send_gps", "/BusTracking-Web/bustracker/api/bus/");
    }};

}
