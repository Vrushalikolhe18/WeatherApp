package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.viewmodel.WeatherViewModel

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    BackgroundImageComponent()
                }
            }
            WeatherAppScreen(viewModel = hiltViewModel())
        }

    }
}

@Composable
fun WeatherAppScreen(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchWeather("787d57355d9d44e0bf9153136242309", "Delhi")
    }

    weatherData?.let { weather ->
        Column(modifier = Modifier.padding(16.dp)) {

                Text("Location: ${weather.location.name}, ${weather.location.country}",
                    fontSize = 24.sp,
                    color = Color.Blue,
                    modifier = Modifier.padding(8.dp))

                Text("Temperature: ${weather.current.temp_c}°C",
                    fontSize = 24.sp,
                    color = Color.Blue,
                    modifier = Modifier.padding(8.dp))

                Text("Condition: ${weather.current.condition.text}",
                    fontSize = 24.sp,
                    color = Color.Blue,
                    modifier = Modifier.padding(8.dp))

            }

    } ?: run {
        Text("Loading...")


    }
}
@Composable
fun BackgroundImageComponent() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.weather),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

    }
}
