package com.app.openweather.dto.forecastweather;

/**
 * Created by Krishnaprasad.n on 9/5/2015.
 */
public class Clouds {
    private String all;

    public String getAll ()
    {
        return all;
    }

    public void setAll (String all)
    {
        this.all = all;
    }

    @Override
    public String toString()
    {
        return " [all = "+all+"]";
    }
}

