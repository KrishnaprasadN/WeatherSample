package com.app.openweather.dto.forecastweather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Krishnaprasad.n on 9/5/2015.
 */
public class Rain {

    @SerializedName("3h")
    private String threeHour;

    public String getThreeHour()
    {
        return threeHour;
    }

    public void setThreeHour(String threeHour)
    {
        this.threeHour = threeHour;
    }

    @Override
    public String toString()
    {
        return "Rain [1h = "+ threeHour +"]";
    }
}


