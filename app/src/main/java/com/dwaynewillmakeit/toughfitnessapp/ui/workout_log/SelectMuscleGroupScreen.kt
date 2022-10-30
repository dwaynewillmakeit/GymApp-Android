package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import com.dwaynewillmakeit.toughfitnessapp.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dwaynewillmakeit.toughfitnessapp.ui.navigation.Destinations
import com.dwaynewillmakeit.toughfitnessapp.ui.theme.ToughFitnessAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectMuscleGroupScreen(navController: NavHostController) {


    Scaffold {

        LazyColumn(modifier = Modifier
            .padding(it)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                Row(modifier = Modifier.padding(bottom = 16.dp)) {
                    MuscleGroupCard(R.drawable.ic_arms, "Arms", "Icon Arms",navController)
                    Spacer(modifier = Modifier.width(16.dp))
                    MuscleGroupCard(R.drawable.ic_abs, "Abdominal", "Icon Abdominal",navController)
                }
            }
            item {
                Row (modifier = Modifier.padding(bottom = 16.dp)){
                    MuscleGroupCard(R.drawable.ic_back, "Back", "Icon Back",navController)
                    Spacer(modifier = Modifier.width(16.dp))
                    MuscleGroupCard(R.drawable.ic_chest, "Chest", "Icon Chest",navController)
                }
            }
            item {
                Row (modifier = Modifier.padding(bottom = 16.dp)){
                    MuscleGroupCard(R.drawable.ic_legs, "Legs & Glutes", "Icon Legs",navController)
                    Spacer(modifier = Modifier.width(16.dp))
                    MuscleGroupCard(R.drawable.ic_shoulders, "Shoulders", "Icon Shoulders",navController)
                }
            }
        }
    }
}

@Composable
private fun MuscleGroupCard(@DrawableRes image: Int, title: String, imageDescription: String,navController: NavHostController) {
    Card(
        modifier = Modifier.size(135.dp).clickable { navController.navigate(Destinations.EXERCISE_LIST) },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = imageDescription,
                modifier = Modifier.size(90.dp)
            )


            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Composable
@Preview
fun SelectMuscleGroupScreenPreview() {

    ToughFitnessAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            SelectMuscleGroupScreen(navController)
        }
    }

}