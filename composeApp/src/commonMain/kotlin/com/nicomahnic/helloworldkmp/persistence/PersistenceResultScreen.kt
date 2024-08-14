package com.nicomahnic.helloworldkmp.persistence

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.russhwolf.settings.Settings

class PersistenceResultScreen : Screen {

    private val settings: Settings = Settings()

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        var name by remember { mutableStateOf(settings.getString(NAME_KEY, "")) }
        var isVip by remember { mutableStateOf(settings.getBoolean(VIP_KEY, false)) }

        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            Text("RESULT")
            Spacer(modifier = Modifier.weight(0.3f))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                enabled = false
            )
            Spacer(modifier = Modifier.weight(0.1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isVip, onCheckedChange = { isVip = it })
                Text("Eres VIP?")
            }
            Spacer(modifier = Modifier.weight(0.1f))
            Button(
                onClick = {
                    navigator.pop()
                    settings.clear()
                },
                enabled = name.isNotEmpty(),
                content = { Text("Volver") }
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }

    companion object {
        private const val NAME_KEY = "NAME"
        private const val VIP_KEY = "VIP"
    }
}