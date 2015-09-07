package com.app.openweather;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.openweather.dto.currentweather.CurrentWeather;
import com.app.openweather.dto.forecastweather.ForecastWeather;
import com.app.openweather.volleyutils.JsonRequestVolley;
import com.app.openweather.volleyutils.VolleyRequestManager;
import com.google.gson.Gson;


import org.json.JSONObject;

/**
 * Created by Krishnaprasad.n on 9/2/2015.
 */
public class WeatherProvider {

    private static WeatherProvider mInstance;
    private Context mContext;
    private CurrentWeather mCurrentWeather = null;
    private ForecastWeather mForecastWeather = null;

    public static WeatherProvider getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new WeatherProvider(context);
        }
        return mInstance;
    }

    private WeatherProvider(Context context) {
        if (mContext == null) {
            mContext = context;
        }
    }

    public void addCurrentWeatherRequest() {
        VolleyRequestManager.getInstance(mContext).getRequestQueue().add(getJsonRequestForCurrentWeather());
    }

    private JsonRequestVolley getJsonRequestForCurrentWeather() {
        JsonRequestVolley request = new JsonRequestVolley(
                Request.Method.GET,
                Constants.URL_CURRENT_WEATHER, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mCurrentWeather = new Gson().fromJson(response.toString(), CurrentWeather.class);
                Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mCurrentWeather = null;
                Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        return request;
    }

    public CurrentWeather getCurrentWeather() {
        return mCurrentWeather;
    }

    public void addForecastWeatherRequest() {
        VolleyRequestManager.getInstance(mContext).getRequestQueue().add(getJsonRequestForecastWeather());
    }

    private JsonRequestVolley getJsonRequestForecastWeather() {
        JsonRequestVolley request = new JsonRequestVolley(
                Request.Method.GET,
                Constants.URL_FORECAST_WEATHER, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mForecastWeather = new Gson().fromJson(response.toString(), ForecastWeather.class);
                Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mCurrentWeather = null;
                Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        return request;
    }

    public ForecastWeather getForecastWeather() {
        return mForecastWeather;
    }
}
