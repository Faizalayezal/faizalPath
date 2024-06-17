package com.example.faizalipathtask.model

data class Hourly(
    val relative_humidity_2m: List<Int>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val wind_speed_10m: List<Double>
)