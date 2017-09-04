package com.sgr.owltube_v2.infra.webapi.response.popular;

public class Medium {

    public String url;
    public Integer width;
    public Integer height;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Medium() {
    }

    /**
     * 
     * @param height
     * @param width
     * @param url
     */
    public Medium(String url, Integer width, Integer height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

}
