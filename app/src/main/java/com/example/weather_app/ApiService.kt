package com.example.weather_app

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("weather")

    suspend fun getWeather(
        @Query("q") cityname:String,
        @Query("appid") apikey:String,
        @Query("lang") lang:String="tr",
        @Query("units") units:String="metric"
    ):Response<WeatherResponse>

}