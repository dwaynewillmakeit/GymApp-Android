package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dwaynewillmakeit.toughfitnessapp.ui.navigation.Destinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateWorkoutScreen(navController: NavHostController) {

    var workoutName by remember { mutableStateOf("") }
    var workoutNotes by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.padding(16.dp), floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(Destinations.SELECT_MUSCLE_GROUP_ROUTE) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Exercise")
        }
    }) {
        Column(modifier = Modifier.padding(it)) {

            OutlinedTextField(
                value = workoutName,
                onValueChange = {it-> workoutName = it},
                label = { Text("Workout Name") },
                placeholder = {
                    Text(
                        text = "Enter workout name"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Row {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Start Date") },
                    placeholder = {
                        Text(
                            text = "Start Date", style = MaterialTheme.typography.bodySmall
                        )
                    },
                    modifier = Modifier.fillMaxWidth(.5f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Start Time") },
                    placeholder = {
                        Text(
                            text = "Start Time", style = MaterialTheme.typography.bodySmall
                        )
                    },
                    modifier = Modifier.fillMaxWidth(1f)
                )
            }

            OutlinedTextField(
                value = workoutNotes,
                onValueChange = { it -> workoutNotes = it},
                label = { Text("Notes") },
                placeholder = {
                    Text(
                        text = "Notes"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false
            )

        }
    }
}