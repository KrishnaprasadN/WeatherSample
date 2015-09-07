package com.app.openweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.app.openweather.dto.currentweather.CurrentWeather;
import com.app.openweather.dto.currentweather.Main;
import com.app.openweather.dto.forecastweather.ForecastWeather;
import com.app.openweather.dto.forecastweather.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        setCurrentWeather();
        setForecastWeather();
    }

    private void setCurrentWeather() {
        CurrentWeather currentWeather = WeatherProvider.getInstance(this).getCurrentWeather();
        if (currentWeather != null) {
            Main main = currentWeather.getMain();
            if (main != null) {
                TextView temp = (TextView) findViewById(R.id.temp);
                temp.setText("Temperature : " + main.getTemp() + "°C");

                TextView pressure = (TextView) findViewById(R.id.pressure);
                pressure.setText("Pressure : " + main.getPressure());

                TextView humidity = (TextView) findViewById(R.id.humidity);
                humidity.setText("Humidity : " + main.getHumidity());
            }
        }
    }

    private void setForecastWeather() {
        //init tab and view pager
        ForecastWeather forecastWeather = WeatherProvider.getInstance(this).getForecastWeather();
        if (forecastWeather != null) {
            List[] lists = forecastWeather.getList();

            if (lists != null && lists.length > 0) {

                TextView forecast = (TextView) findViewById(R.id.text);
                String info = "";
                for (List list: lists) {
                    info += "\n Date : " + list.getDt_txt() + " - " + list.getMain().getTemp() + "°C";
                }

                forecast.setText(info);
            }
        }
    }
}
