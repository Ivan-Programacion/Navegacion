package com.example.navegacion.Controller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navegacion.View.Home
import com.example.navegacion.View.Login
import com.example.navegacion.ui.theme.NavegacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacionTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Main(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Main(paddingValues: PaddingValues = PaddingValues()){ // Dejamos valor por defecto para la view
    // El remember de una navegación se hace con rememberNavController()
    val navController = rememberNavController()
    // NavHost --> Descripción de ventanas/pantallas
    NavHost(navController, startDestination = "login"){
        // Lista de composables
        composable("login") {
            // Con navigate() se cambia de ventana
            // Indicas la ruta desde la etiqueta indicada + información (pasar valores por parámetro)
            Login(paddingValues) { navController.navigate("home/${it.userName}/${it.userAge}") }}
        // Pasas por argumento las etiquetas, que luego serán los valores (como args en Java)
        // Las llaves indicas que son keys
        // Se hace así para separar logicas entre ventanas
        composable("home/{userName}/{userAge}") {
            val userName = it.arguments?.getString("userName")
            val userAge = it.arguments?.getString("userAge")
            Home(paddingValues, userName, userAge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    NavegacionTheme {
        Login(onLogin = {} )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    NavegacionTheme {
        Home(userName = "", userAge = "")
    }
}