package com.rnlanscan.utils;



import java.net.InetAddress;

import android.util.Log;

public class Ping {

    private  final String TAG = "Ping";
    private  final String CMD = "/system/bin/ping -q -n -w 1 -c 1 %s";
    private  final int TIMEOUT = 1000;

    public  boolean ping(String host) {
        Runtime runtime = Runtime.getRuntime();
        Process pingProcess = null;
        try {
            // TODO: Use ProcessBuilder ?
            
            // Runtime.getRuntime().exec(String.format(CMD, host));
            pingProcess = runtime.exec(String.format(CMD,host));
            int pingResult = pingProcess.waitFor();
            Log.d(TAG, "PING RESULT" + String.valueOf(pingResult));
            if(pingResult == 0){
                return true;
            }else{
                InetAddress address = InetAddress.getByName(host);
                boolean isReachable = address.isReachable(500);
                if(isReachable){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (Exception e) {
           return false;
    }
}
}