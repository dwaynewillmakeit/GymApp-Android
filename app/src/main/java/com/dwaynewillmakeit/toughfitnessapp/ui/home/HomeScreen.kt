package com.dwaynewillmakeit.toughfitnessapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLog
import com.dwaynewillmakeit.toughfitnessapp.ui.destinations.WorkoutLogScreenDestination
import com.dwaynewillmakeit.toughfitnessapp.ui.theme.ToughFitnessAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination(start = true)
fun HomeScreen(navigator: DestinationsNavigator, viewModel: HomeViewModel = hiltViewModel()) {


    val state = viewModel.state

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {navigator.navigate(WorkoutLogScreenDestination(workoutLogUUID = null))  }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Log Workout")
        }
    }) {
        val modifier = Modifier.padding(it);
        Column(
            modifier = modifier
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

                if (state.upcomingWorkouts.isNotEmpty()) {

                    state.upcomingWorkouts.forEach {

                        item {

                            UpcomingWorkoutCard(it)
                        }
                    }

                } else {
                    item {
                        Column(Modifier.fillMaxWidth()) {
                            Text(
                                text = "No active or upcoming workouts",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Thin,
                                color = MaterialTheme.colorScheme.onSurface.copy(.5f)
                            )
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Recent Workouts", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                if (state.recentWorkouts.isNotEmpty()) {
                    state.recentWorkouts.forEach {
                        item {

                            RecentWorkoutCard(it,navigator)
                        }
                    }
                } else {
                    item {
                        Column(Modifier.fillMaxWidth()) {
                            Text(
                                text = "No recent workouts",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Thin,
                                color = MaterialTheme.colorScheme.onSurface.copy(.5f)
                            )
                        }
                    }
                }


            }

        }
    }
}

@Composable
private fun UpcomingWorkoutCard(workoutLog: WorkoutLog) {
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
                workoutLog.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            WorkoutDateContainer(workoutLog.startDateTime)
        }
    }
}

@Composable
private fun WorkoutDateContainer(startDateTime: LocalDateTime) {

    val month = startDateTime.month
    val day = startDateTime.dayOfMonth
    val year = startDateTime.year
    Column(
        modifier = Modifier
            .size(
                90.dp
            )
            .background(color = MaterialTheme.colorScheme.onSurfaceVariant),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = startDateTime.dayOfWeek.name.substring(0, 3),
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = day.toString(),
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = month.name.substring(0, 3),
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = year.toString(),
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun RecentWorkoutCard(workoutLog: WorkoutLog, navigator: DestinationsNavigator) {


    val time = workoutLog.startDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"))

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { navigator.navigate(WorkoutLogScreenDestination(workoutLogUUID = workoutLog.guid))},
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            WorkoutDateContainer(workoutLog.startDateTime)

            Spacer(modifier = Modifier.width(24.dp))

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = workoutLog.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = time, style = MaterialTheme.typography.bodyMedium)
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
//            HomeScreen()
        }
    }

}

