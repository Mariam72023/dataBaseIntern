package com.example.mydatabase_intern.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.room.Room
import com.example.mydatabase_intern.room.MyDatabase
import com.example.mydatabase_intern.room.User

@Composable
fun Screen1(navController: NavController) {
    val context = LocalContext.current
    var userName by remember {
        mutableStateOf("")
    }
    var age by remember {
        mutableStateOf(0)
    }
    val database = Room.databaseBuilder(
        context = context,
        klass = MyDatabase::class.java,
        name = "MyDatabase"
    ).allowMainThreadQueries().build()
    val userDao = database.userDao()
    val data by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text(text = "enter username") })
        OutlinedTextField(
            value = age.toString(),
            onValueChange = { age = it.toIntOrNull() ?: 0 },
            label = { "enter age" })
        Button(onClick = {
            userDao.inserUser(
                user = User(
                    name = userName,
                    age = age

                )
            )
            Toast.makeText(context, "added successfully", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "insert data")

        }
        Button(onClick = {
           // data = userDao.getAllUsers().joinToString(separator = "\n")
            navController.navigate("list")
        }) {
            Text(text = "retrieve data")
        }
     //   Text(text = data)
    }
}