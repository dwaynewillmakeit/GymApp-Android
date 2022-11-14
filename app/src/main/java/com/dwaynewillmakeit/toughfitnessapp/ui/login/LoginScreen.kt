package com.dwaynewillmakeit.toughfitnessapp.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dwaynewillmakeit.toughfitnessapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Login", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value = "johndoe@mail.com",
            onValueChange = {  },
            label = { Text("Email")
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon", tint = MaterialTheme.colorScheme.primary) },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)

        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = "password",
            onValueChange = {  },
            label = { Text("Password") },
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_lock_24), contentDescription = "password", tint = MaterialTheme.colorScheme.primary) },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)


            )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {/*TODO*/}, modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .width(300.dp)
            .height(55.dp)) {
            Text(text = "Login",style = MaterialTheme.typography.bodyLarge)
        }



    }
}