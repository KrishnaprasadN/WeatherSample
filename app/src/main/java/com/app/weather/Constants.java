package com.app.weather;

/**
 * Created by Krishnaprasad.n on 9/2/2015.
 */
public class Constants {
    public static final int SPLASH_TIMEOUT = 3000;
    //Server url
    private static final String BASE_URL = "http://api.openweathermap.org/";
    public static final String URL_CURRENT_WEATHER = BASE_URL + "data/2.5/weather?q=mangalore&units=metric";

    public static final String URL_FORECAST_WEATHER = BASE_URL + "data/2.5/forecast?lat=12.8700&lon=74.8800&units=metric";

}
