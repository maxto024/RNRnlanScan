package com.rnlanscan.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.net.InetAddress;

import android.util.Log;

public class Ping {

    private  final String TAG = "Ping";
    private  final String CMD = "/system/bin/ping  -c  %s";
    private  final int TIMEOUT = 1000;

    public  boolean ping(String host) {
        
     try {
         
    ProcessBuilder probuilder = new ProcessBuilder("ping", "-c", "1", host);
    Process process = probuilder.start();

    InputStream is = process.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    String line;
    boolean isAvailable = false;
   
    while ((line = br.readLine()) != null) {
        CharSequence isPacketsTransmitted = "1 packets transmitted,";
        CharSequence hasErrors = "+1 errors,";
        if(!line.contains(hasErrors) && line.contains(isPacketsTransmitted)){
            isAvailable = true;
        }
        }
        return isAvailable;
    } catch (Exception e) {
           return false;
    }
}
}