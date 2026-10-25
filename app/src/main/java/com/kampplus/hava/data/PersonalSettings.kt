package com.kampplus.hava.data

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kampplus.hava.model.City

class PersonalSettings(context: Context) {

    private val prefs = context.applicationContext.getSharedPreferences(
        "personal_weather",
        Context.MODE_PRIVATE
    )

    private val gson = Gson()

    var theme by mutableStateOf(
        prefs.getString("theme", "Sistem") ?: "Sistem"
    )
        private set

    var favorites by mutableStateOf(readCities("favorites"))
        private set

    var recent by mutableStateOf(readCities("recent"))
        private set

    private fun readCities(key: String): List<City> {
        return try {
            val type = object : TypeToken<List<City>>() {}.type
            gson.fromJson<List<City>>(
                prefs.getString(key, "[]"),
                type
            ) ?: emptyList()
        } catch (_: RuntimeException) {
            emptyList()
        }
    }

    fun changeTheme(value: String) {
        theme = value
        prefs.edit().putString("theme", value).apply()
    }

    fun toggleFavorite(city: City) {
        favorites = if (favorites.any { it.id == city.id }) {
            favorites.filterNot { it.id == city.id }
        } else {
            favorites + city
        }

        prefs.edit()
            .putString("favorites", gson.toJson(favorites))
            .apply()
    }

    fun remember(city: City) {
        recent = (
            listOf(city) + recent.filterNot { it.id == city.id }
            ).take(5)

        prefs.edit()
            .putString("recent", gson.toJson(recent))
            .apply()
    }

    fun clearRecent() {
        recent = emptyList()
        prefs.edit().remove("recent").apply()
    }
}

val LocalPersonalSettings = staticCompositionLocalOf<PersonalSettings?> {
    null
}
