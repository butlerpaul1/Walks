package com.example.paul.myapplication.api.service;

import android.content.Context;
import android.util.Log;
import com.example.paul.myapplication.api.model.WalkImageFilter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by butle on 3/28/2018.
 */

public class WalkImageService {

    private static final String API_BASE_URL = "https://www.googleapis.com/customsearch/v1?";
    private static final String API_KEY = "AIzaSyAaqcEy7m2ktM5H0wQ8ehIiiYA6PfZ84aY";
    private static final String CX_KEY = "009799710231875569713:ujfpwlnxphy";



    private AsyncHttpClient client;

    public WalkImageService(){
        this.client = new AsyncHttpClient();
    }

    private String getApiUrl(String relativeUrl){
        return API_BASE_URL + relativeUrl;   }


    public String getFilterUrl (WalkImageFilter walkImageFilter){
        String filterUrl = "";
        if (walkImageFilter.getColor() != null && !walkImageFilter.getColor().equals("Any")){
            filterUrl += "&imgDominantColor=" + walkImageFilter.getColor();
        }
        if (walkImageFilter.getSize() != null && !walkImageFilter.getSize().equals("Any")){
            filterUrl += "&imgSize=" + walkImageFilter.getSize();
        }
        if (walkImageFilter.getType() != null && !walkImageFilter.getType().equals("Any")){
            filterUrl += "&imgType=" + walkImageFilter.getType();
        }
        if (walkImageFilter.getSite() != null && !walkImageFilter.getSite().equals("")){
            filterUrl += "&siteSearch=" + walkImageFilter.getSite();
        }

        return filterUrl;
    }
    public void getSearch(final String query, int startPage, WalkImageFilter walkImageFilter, JsonHttpResponseHandler handler ){
        try {
            String url = getApiUrl("q="+URLEncoder.encode(query,"utf-8")+"&start="+startPage+
                    "&cx="+CX_KEY+"&searchType=image"+getFilterUrl(walkImageFilter)+"&key="+API_KEY);
            client.get(url, handler);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            Log.d("getSearch", "Search not working");
        }
    }
}
