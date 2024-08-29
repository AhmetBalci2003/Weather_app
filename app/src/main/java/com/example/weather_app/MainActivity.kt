package com.example.weather_appmport

import android.annotation.SuppressLint
import com.example.weather_app.R


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_app.WeatherResponse
import com.example.weather_app.fetchweather
import com.example.weather_app.ui.theme.Weather_appTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Weather_appTheme {
                WeatherScreen()
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WeatherScreen()
{    var showdialog:Boolean by remember {
    mutableStateOf(true)
}
    var cityname by remember {
        mutableStateOf("")
    }
    var weatherresponse by remember {
        mutableStateOf<WeatherResponse?>(null)
    }
    val apikey="-----apikey-------"
    val coroutinescope= rememberCoroutineScope()
    if(showdialog)
    {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = "şehrinizi giriniz") },
            text = {
                OutlinedTextField(value = cityname, onValueChange = { cityname = it })
            },

            confirmButton = {
                Button(onClick = { showdialog=false }) {

                }
            },
        )
    }
    else{
        coroutinescope.launch {
            weatherresponse = fetchweather(cityname, apikey)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Şehir: $cityname", fontSize = 20.sp)
            weatherresponse?.let {
                Text(text = "Sıcaklık: ${it.main.temp}°C")
                Text(text = "Açıklama: ${it.weather[0].description}")
                // İkonu ekleyin (örneğin, `icon` kodunu kullanarak resmi elde edin)
                // Bu örnek, statik bir resim kullanır
                Image(
                    painter = painterResource(id = getWeatherIconId(it.weather[0].icon)),
                    contentDescription = "Hava Durumu İkonu",
                    modifier = Modifier.size(100.dp)
                )
            }

        }
    }

}

@Composable
fun getWeatherIconId(iconCode: String): Int {
    return when (iconCode) {
        "01d" -> R.drawable._01d
        "02d" -> R.drawable._02d
        "03d" -> R.drawable._03d
        "04d" -> R.drawable._04d
        "09d" -> R.drawable._09d
        "10d" -> R.drawable._10d
        "11d" -> R.drawable._11d
        "13d" -> R.drawable._13d
        "50d" -> R.drawable._50d
        "01n" -> R.drawable._01n
        "02n" -> R.drawable._02n
        "03n" -> R.drawable._03n
        "04n" -> R.drawable._04n
        "09n" -> R.drawable._09n
        "10n" -> R.drawable._10n
        "11n" -> R.drawable._11n
        "13n" -> R.drawable._13n
        "50n" -> R.drawable._50n
        else -> R.drawable.ic_launcher_foreground
    }
}


