package com.app.openweather.dto.forecastweather;

/**
 * Created by Krishnaprasad.n on 9/5/2015.
 */
public class SysPopulation {
    private String population;

    public String getPopulation ()
    {
        return population;
    }

    public void setPopulation (String population)
    {
        this.population = population;
    }

    @Override
    public String toString()
    {
        return "[population = "+population+"]";
    }
}
