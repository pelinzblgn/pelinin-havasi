package com.kampplus.hava.model

import java.time.LocalDateTime
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ForecastPresentationTest {
    private val cityTime = LocalDateTime.of(2026, 10, 25, 23, 15)
    private fun forecast() = Forecast(
        current = CurrentWeather(20.0, 0, cityTime),
        hourly = (0..30).map {
            HourlyForecast(cityTime.toLocalDate().atTime(22, 0).plusHours(it.toLong()), 20.0, 0, 0)
        },
        daily = emptyList()
    )

    @Test
    fun `hourly selection uses city data time and crosses midnight`() {
        val hours = forecast().upcomingHours()
        assertEquals(24, hours.size)
        assertEquals(cityTime.withMinute(0), hours.first().time)
        assertEquals(cityTime.toLocalDate().plusDays(1), hours[1].time.toLocalDate())
    }

    @Test
    fun `zero rain differs from missing rain`() {
        assertEquals("%0", rainLabel(0))
        assertEquals("—", rainLabel(null))
    }

    @Test
    fun `rain advice examines next six hours only`() {
        val base = forecast()
        val lateRain = base.copy(hourly = base.hourly.mapIndexed { i, h ->
            h.copy(precipitationProbability = if (i >= 8) 90 else 0)
        })
        assertTrue(lateRain.planningNotes().isEmpty())
        val soonRain = base.copy(hourly = base.hourly.map { it.copy(precipitationProbability = 60) })
        assertEquals(1, soonRain.planningNotes().size)
    }
}
