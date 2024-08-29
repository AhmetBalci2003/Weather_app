package com.example.weather_app

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit


suspend fun fetchweather(cityname:String, apikey:String): WeatherResponse? {
    return withContext(Dispatchers.IO){
        try {
            val response = RetrofitClient.apiservice.getWeather(cityname, apikey)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }catch (e:Exception){
            Log.e("exeption","${e.message}")
        } as WeatherResponse?

    }

}