package com.deltaqin.sys.remote_data.config;

public class PrestoConfig {
    private static String url = "118.195.132.110";
    private static int socket = 10001;

    public static String addUrl(){
        return "http://"+url+":"+socket+"/add";
    }

    public static String delUrl(){
        return "http://"+url+":"+socket+"/del";
    }

    public static String uptUrl(){
        return "http://"+url+":"+socket+"/upt";
    }
}
