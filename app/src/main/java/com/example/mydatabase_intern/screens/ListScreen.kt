package com.example.mydatabase_intern.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mydatabase_intern.room.MyDatabase
import com.example.mydatabase_intern.room.User

@Composable
fun ListScreen(list: List<User>, navController: NavController, database: MyDatabase) {
    LazyColumn(modifier = Modifier.padding(10.dp)) {
        if (list.isEmpty()) {
            item { Text(text = "No user added") }
        } else {
            items(list) {
                UserItem(user = it) {
                    navController.navigate("userDetails/${it.id}")
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User, onItemClick: () -> Unit) {
    Card(
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(5.dp),
    ) {
        Text(text = user.name, fontSize = 20.sp, modifier = Modifier.padding(8.dp))
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.padding(8.dp)) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "")
            }
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
            }
        }
    }
}

@Composable
fun DialogUpdate(onUpdateClick:(User?)->Unit) {
    var name by remember {
        mutableStateOf("")
    }
    var age by remember {
        mutableStateOf(0)
    }

    AlertDialog(onDismissRequest = { }, title = { Text(text = "edit profile") }, text = {
        Column {
            TextField(value = name, onValueChange = { name = it }, label = { Text(text = "Name") })
            TextField(value = age.toString(), onValueChange = { age = it.toIntOrNull()?:0 }, label = { Text(text = "Age") })
        }
    }
    , confirmButton = {
        Button(onClick = { onUpdateClick(User(name=name,age=age)) }) {
Text(text = "OK")
        }

        }
    , dismissButton = {
        Button(onClick = { onUpdateClick(null)}) {

        }
        }
    )
}

@Preview
@Composable
fun Item() {

    UserItem(user = User(1, "mariam", 1)) {

    }
}

