package com.example.navegacion.View

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import com.example.navegacion.Controller.checkValidData
import com.example.navegacion.Model.User

@Composable
fun Login(paddingValues: PaddingValues = PaddingValues(), onLogin: (User) -> Unit) {
    Column(
        Modifier.padding(paddingValues).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var userName by remember { mutableStateOf("") }
        var userAge by remember { mutableStateOf("") }
        OutlinedTextField(
            userName,
            {userName = it},
        )
        OutlinedTextField(
            userAge,
            {userAge = it},
            keyboardOptions =  KeyboardOptions(keyboardType =  KeyboardType.Number)
        )
        Button({
             if (checkValidData(userName, userAge)) {
                 // Se crea un nuevo usuario y le pasamos el nombre y la edad para pasarlo al login
                 val newUser = User(userName, userAge)
                 onLogin(newUser)
             }
        }) { Text("Log In") }
    }
}