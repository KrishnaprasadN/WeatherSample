package com.app.weather.dto.currentweather;

/**
 * Created by Krishnaprasad.n on 9/5/2015.
 */
public class Coord {
    private String lon;

    private String lat;

    public String getLon ()
    {
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @Override
    public String toString()
    {
        return "Coord [lon = "+lon+", lat = "+lat+"]";
    }
}


