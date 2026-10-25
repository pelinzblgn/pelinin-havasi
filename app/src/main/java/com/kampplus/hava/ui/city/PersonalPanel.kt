package com.kampplus.hava.ui.city

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kampplus.hava.data.PersonalSettings
import com.kampplus.hava.model.City

@Composable
fun PersonalPanel(
    settings: PersonalSettings,
    onCityClick: (City) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Merhaba Pelin ☀",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Günün planı, havan kadar sana özel.",
            style = MaterialTheme.typography.bodyMedium
        )

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Sistem", "Açık", "Koyu").forEach { theme ->
                FilterChip(
                    selected = settings.theme == theme,
                    onClick = { settings.changeTheme(theme) },
                    label = { Text(theme) }
                )
            }
        }

        if (settings.favorites.isNotEmpty()) {
            Text(
                text = "Favori şehirlerin",
                style = MaterialTheme.typography.titleSmall
            )

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                settings.favorites.forEach { city ->
                    AssistChip(
                        onClick = { onCityClick(city) },
                        label = { Text("★ ${city.name}") }
                    )
                }
            }
        }

        if (settings.recent.isNotEmpty()) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Son baktıkların",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = 12.dp)
                )

                TextButton(onClick = { settings.clearRecent() }) {
                    Text("Temizle")
                }
            }

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                settings.recent.forEach { city ->
                    AssistChip(
                        onClick = { onCityClick(city) },
                        label = { Text(city.name) }
                    )
                }
            }
        }
    }
}
