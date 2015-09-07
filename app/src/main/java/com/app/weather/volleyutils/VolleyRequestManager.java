package com.app.weather.volleyutils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Krishnaprasad.n on 9/2/2015.
 */
public class VolleyRequestManager {

    private static VolleyRequestManager mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private VolleyRequestManager(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleyRequestManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyRequestManager(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
