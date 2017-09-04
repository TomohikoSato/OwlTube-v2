package com.sgr.owltube_v2.infra.webapi.response.popular;

public class Thumbnails {

    public Default _default;
    public Medium medium;
    public High high;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Thumbnails() {
    }

    /**
     * 
     * @param _default
     * @param high
     * @param medium
     */
    public Thumbnails(Default _default, Medium medium, High high) {
        this._default = _default;
        this.medium = medium;
        this.high = high;
    }

}
