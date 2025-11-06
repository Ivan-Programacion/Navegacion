package com.example.navegacion.Controller

fun checkValidData(userName: String, userAge: String): Boolean {
    return userName.isNotEmpty() && userAge.isNotEmpty()
}