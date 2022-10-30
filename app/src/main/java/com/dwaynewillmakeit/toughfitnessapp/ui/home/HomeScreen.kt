package com.dwaynewillmakeit.toughfitnessapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dwaynewillmakeit.toughfitnessapp.R
import com.dwaynewillmakeit.toughfitnessapp.ui.navigation.Destinations
import com.dwaynewillmakeit.toughfitnessapp.ui.navigation.Destinations.CREATE_WORKOUT_ROUTE
import com.dwaynewillmakeit.toughfitnessapp.ui.theme.ToughFitnessAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(CREATE_WORKOUT_ROUTE) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Log Workout")
        }
    }) {
        val modifier = Modifier.padding(it);
        HomeScreenContent(navController)
    }
}

@Composable
private fun HomeScreenContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {


        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Active/ Upcoming Workouts", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(
                12.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            item {

                UpcomingWorkoutCard()
            }
            item {

                UpcomingWorkoutCard()
            }
            item {

                UpcomingWorkoutCard()
            }
            item {

                UpcomingWorkoutCard()
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Recent Workouts", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            for (i in 1..10) {
                item {

                    RecentWorkoutCard()
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { navController.navigate(Destinations.LOGIN_ROUTE) }, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(300.dp)
                .height(55.dp)
        ) {
            Text(text = "Back to login", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
private fun UpcomingWorkoutCard() {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.size(150.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Back & Biceps",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            WorkoutDateContainer()
        }
    }
}

@Composable
private fun WorkoutDateContainer() {
    Column(
        modifier = Modifier
            .size(
                90
                    .dp
            )
            .background(color = MaterialTheme.colorScheme.onSurfaceVariant),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Mon",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "17",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Oct",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "2022",
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun RecentWorkoutCard() {

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            WorkoutDateContainer()

            Spacer(modifier = Modifier.width(24.dp))

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Chest & Triceps Day",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "6:30pm", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Start Time", style = MaterialTheme.typography.bodySmall)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "90 mins", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Duration", style = MaterialTheme.typography.bodySmall)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "1500lbs", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Total Weight", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {

    ToughFitnessAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            HomeScreen(navController)
        }
    }

}

