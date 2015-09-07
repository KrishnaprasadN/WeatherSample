package com.app.openweather.volleyutils;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by Krishnaprasad.n on 9/2/2015.
 */
public class JsonRequestVolley extends JsonObjectRequest {

    private int mStatusCode;

    public JsonRequestVolley(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        mStatusCode = response.statusCode;
        return super.parseNetworkResponse(response);
    }

    public int getStatusCode() {
        return mStatusCode;
    }
}
