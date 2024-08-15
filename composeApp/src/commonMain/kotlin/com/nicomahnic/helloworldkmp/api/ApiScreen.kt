package com.nicomahnic.helloworldkmp.api

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.nicomahnic.helloworldkmp.api.network.NetworkUtils.URL
import com.nicomahnic.helloworldkmp.api.network.NetworkUtils.httpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ApiScreen : Screen {

    @Composable
    override fun Content() {
        var response by remember { mutableStateOf("Esperando...") }
        Column(modifier = Modifier.fillMaxSize()) {
            Button(onClick = {
                response = "Cargando..."
                CoroutineScope(Dispatchers.IO).launch {
                   val res = httpClient.get(URL)
                    response = res.bodyAsText()
                }
            }) { Text("Buscar") }
            Text(response)
        }
    }

}