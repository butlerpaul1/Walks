package com.example.paul.myapplication.api.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by butle on 3/28/2018.
 */

public class WalkImageResult implements Serializable{
    private String fullUrl;
    private String thumbUrl;
    private String title;

    public String getFullUrl() {
        return fullUrl;
    }
    public String getThumbUrl() {
        return thumbUrl;
    }
    public String getTitle() {
        return title;
    }

    public WalkImageResult(JSONObject json){
        try {
            this.fullUrl = json.getString("link");
            //this.title = json.getString("title");
            this.thumbUrl = json.getJSONObject("image").getString("thumbnailLink");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<WalkImageResult> fromJSONArray (JSONArray array){
        ArrayList<WalkImageResult> results = new ArrayList<>();
        for (int i=0; i < array.length(); i++){
            try {
                results.add(new WalkImageResult(array.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return results;
    }

}
