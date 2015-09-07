package com.app.weather.dto.currentweather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Krishnaprasad.n on 9/5/2015.
 */
public class Rain {

    @SerializedName("1h")
    private String hour;

    public String getHour ()
    {
        return hour;
    }

    public void setHour (String hour)
    {
        this.hour = hour;
    }

    @Override
    public String toString()
    {
        return "Rain [1h = "+hour+"]";
    }
}


