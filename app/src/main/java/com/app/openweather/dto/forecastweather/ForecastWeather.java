package com.app.openweather.dto.forecastweather;

import com.app.openweather.dto.currentweather.Clouds;
import com.app.openweather.dto.currentweather.Coord;
import com.app.openweather.dto.currentweather.Main;
import com.app.openweather.dto.currentweather.Rain;
import com.app.openweather.dto.currentweather.Sys;
import com.app.openweather.dto.currentweather.Weather;
import com.app.openweather.dto.currentweather.Wind;

/**
 * Created by Krishnaprasad.n on 9/5/2015.
 */
public class ForecastWeather {
    private String message;

    private String cnt;

    private String cod;

    private List[] list;

    private City city;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getCnt ()
    {
        return cnt;
    }

    public void setCnt (String cnt)
    {
        this.cnt = cnt;
    }

    public String getCod ()
    {
        return cod;
    }

    public void setCod (String cod)
    {
        this.cod = cod;
    }

    public List[] getList ()
    {
        return list;
    }

    public void setList (List[] list)
    {
        this.list = list;
    }

    public City getCity ()
    {
        return city;
    }

    public void setCity (City city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return " [message = "+message+", cnt = "+cnt+", cod = "+cod+", list = "+list+", city = "+city+"]";
    }
}

