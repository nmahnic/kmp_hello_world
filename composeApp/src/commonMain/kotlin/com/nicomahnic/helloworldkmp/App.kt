package com.nicomahnic.helloworldkmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.nicomahnic.helloworldkmp.bottomBar.BottomBarScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(screen = MainScreen()) { navigator ->
//            FadeTransition(navigator)
//            ScaleTransition(navigator)
//            SlideTransition(navigator)
            CrossfadeTransition(navigator)
        }
    }
}

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Green),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { navigator.push(SecondScreen()) }) {
                Text("Navegar a ROJO")
            }
            Button(onClick = { navigator.push(ThirdScreen()) }) {
                Text("Navegar a AZUL")
            }
            Button(onClick = { navigator.push(BottomBarScreen()) }) {
                Text("Navegar a BottomBarScreen")
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
                Text("VOLVER A VERDE")
            }
        }
    }
}

class ThirdScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Blue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text("Tercera Pantalla", fontSize = 26.sp, color = Color.White)
            Button(onClick = { navigator.pop() }) {
                Text("VOLVER A VERDE")
            }
        }
    }
}