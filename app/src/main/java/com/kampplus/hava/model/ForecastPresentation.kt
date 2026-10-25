package com.kampplus.hava.model

import java.time.temporal.ChronoUnit

/** API current time is already local to the selected city, unlike the phone clock. */
fun Forecast.upcomingHours(): List<HourlyForecast> {
    val start = current.observedAt.truncatedTo(ChronoUnit.HOURS)
    return hourly.filter { !it.time.isBefore(start) }.take(24)
}

fun rainLabel(value: Int?): String = value?.let { "%$it" } ?: "—"

/** Simple suggestions from forecast values, not official weather warnings. */
fun Forecast.planningNotes(): List<String> = buildList {
    val rain = upcomingHours().take(6).mapNotNull { it.precipitationProbability }.maxOrNull()
    if (rain != null && rain >= 50) add("Önümüzdeki 6 saatte yağış olasılığı %$rain seviyesine ulaşıyor. Şemsiye almak iyi olabilir.")
    val feels = current.apparentTemperatureC ?: current.temperatureC
    if (feels <= 10) add("Hissedilen sıcaklık düşük. Dışarı çıkarken ek bir kat giysi alabilirsin.")
    if (feels >= 30) add("Hava sıcak hissediliyor. Açık hava planında su ve gölge molalarını düşün.")
    if ((current.windSpeedKmh ?: 0.0) >= 35) add("Rüzgâr hızı yüksek. Açık hava planını buna göre gözden geçir.")
}
