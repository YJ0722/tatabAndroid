package com.example.lg.tatab01;

/**
 * Created by a on 2018-11-19.
 */

public class URLVO {

    //private final static String ip = "http://192.168.1.14:8000/tatab";
    private final static String ip = "http://192.168.1.3:8000/tatab";
    private String url;
    private String mappingUrl;


    public URLVO(String mappingUrl) {
        this.mappingUrl = mappingUrl;
        this.url = this.ip + "/" + mappingUrl;
    }

    public static String getIp() {
        return ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMappingUrl() {
        return mappingUrl;
    }

    public void setMappingUrl(String mappingUrl) {
        this.mappingUrl = mappingUrl;
    }

    @Override
    public String toString() {
        return "URLVO{" +
                "url='" + url + '\'' +
                ", mappingUrl='" + mappingUrl + '\'' +
                '}';
    }
}