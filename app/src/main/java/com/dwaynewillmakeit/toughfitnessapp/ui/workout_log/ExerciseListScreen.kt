package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dwaynewillmakeit.toughfitnessapp.ui.theme.ToughFitnessAppTheme
import com.dwaynewillmakeit.toughfitnessapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListScreen(navController: NavController) {

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn(modifier = Modifier
                .padding(it)
                .fillMaxHeight(.9f)
                .fillMaxWidth()) {

                for ( i in 1..15){

                    item {
                        ExerciseCard()
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Add to my plan")
            }
        }

    }
}

@Composable
private fun ExerciseCard() {

    var isSelected by remember{ mutableStateOf(false) }

    Card(
        modifier = Modifier
            .height(95.dp)
            .width(345.dp).clickable { isSelected = !isSelected },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .size(74.dp)
                    .border(BorderStroke(1.dp, Color.Black))
            ) {

                Image(
                    painter = painterResource(id = R.drawable.dumbell_decline_bench_press),
                    contentDescription = "", contentScale = ContentScale.Crop
                )
            }

            Text(
                text = "Dumbbell Decline Bench Press",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth(0.7f)
            )

            Column() {
                Checkbox(checked = isSelected, onCheckedChange = {isSelected = it})
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "")
                }
            }
        }
    }
}

@Composable
@Preview
fun ExerciseListScreenPreview() {

    ToughFitnessAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            ExerciseListScreen(navController)
        }
    }

}