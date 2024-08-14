package com.nicomahnic.helloworldkmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.CrossfadeTransition

@Composable
fun App() {
    MaterialTheme {
        Navigator(screen = MainScreen()) { navigator ->
//            SlideTransition(navigator)
            CrossfadeTransition(navigator)
//            FadeTransition(navigator)
//            ScaleTransition(navigator)
        }
    }
}

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navigator.push(SecondScreen()) }) {
                Text("Navegacion Basica")
            }
        }
    }
}

class SecondScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Red),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text("Secunda Pantalla", fontSize = 26.sp, color = Color.White)
            Button(onClick = { navigator.pop() }) {
                Text("Navegacion Basica")
            }
        }
    }
}