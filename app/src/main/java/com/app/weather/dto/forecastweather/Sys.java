package com.app.weather.dto.forecastweather;

/**
 * Created by Krishnaprasad.n on 9/5/2015.
 */
public class Sys {
    private String pod;

    public String getPod ()
    {
        return pod;
    }

    public void setPod (String pod)
    {
        this.pod = pod;
    }

    @Override
    public String toString()
    {
        return " [pod = "+pod+"]";
    }
}

