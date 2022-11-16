package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dwaynewillmakeit.toughfitnessapp.ui.destinations.SelectMuscleGroupScreenDestination
import com.dwaynewillmakeit.toughfitnessapp.ui.theme.ToughFitnessAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun WorkoutLogScreen(
    navigator: DestinationsNavigator,
    viewModel: WorkoutLogViewModel = hiltViewModel(),
    resultRecipient: ResultRecipient<SelectMuscleGroupScreenDestination, String>
) {
    val state = viewModel.state

    val TAG = "WorkoutLogScreen: "

    Log.i(TAG, state.workoutLogExercises.toString())

    resultRecipient.onNavResult { result ->
        when (result) {
            is NavResult.Canceled -> {
                // `GoToProfileConfirmationDestination` was shown but it was canceled
                // and no value was set (example: dialog/bottom sheet dismissed)
            }
            is NavResult.Value -> {
                Log.i(TAG, result.value)

                viewModel.addExercisesToLog(Json.decodeFromString(result.value))

            }
        }
    }


    Scaffold(modifier = Modifier.padding(16.dp), floatingActionButton = {
        FloatingActionButton(onClick = { navigator.navigate(SelectMuscleGroupScreenDestination) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Exercise")
        }
    }) { it ->

        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            item {

                Column(modifier = Modifier.padding(it)) {

                    OutlinedTextField(
                        value = state.workoutName,
                        onValueChange = { it -> viewModel.setWorkoutName(it) },
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
                        value = state.notes,
                        onValueChange = { it -> viewModel.setNotes(it) },
                        label = { Text("Notes") },
                        placeholder = {
                            Text(
                                text = "Notes"
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = false
                    )


                    state.workoutLogExercises.forEach { workoutLogExercise ->

                        ViewExerciseSet(
                            workoutLogExercise, viewModel
                        )

                    }


                }
            }
        }
    }
}

@Composable
fun ViewExerciseSet(
    workoutLogExerciseState: Map.Entry<String, WorkoutLogExerciseState>,
    viewModel: WorkoutLogViewModel
) {

    Spacer(modifier = Modifier.height(12.dp))

    val columnWeight = listOf(1F, 1.5F, 1F, 2F, 1F)

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = workoutLogExerciseState.value.workoutLogExercise.exercise_name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
        }

    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
            .padding(12.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Sets",
                Modifier.weight(columnWeight.component1()),
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
            )
            Text(
                text = "Weight",
                Modifier.weight(columnWeight.component2()),
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
            )
            Text(
                text = "Rep",
                Modifier.weight(columnWeight.component3()),
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
            )
            Text(
                text = "Notes",
                Modifier.weight(columnWeight.component4()),
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "",
                Modifier.weight(columnWeight.component5()),
                fontWeight = FontWeight.Bold
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )

        val workoutSets = workoutLogExerciseState.value.workoutSets.values

        workoutSets.forEachIndexed {index, workoutSets ->

            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = workoutSets.set.toString(),
                    Modifier.weight(columnWeight.component1()),
                    textAlign = TextAlign.Center, fontSize = 14.sp
                )
                Text(
                    text = "${workoutSets.weight}lbs",
                    Modifier
                        .weight(columnWeight.component2()),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
                Text(
                    text = "${workoutSets.repCount}",
                    Modifier.weight(columnWeight.component3()),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
                Text(
                    text = workoutSets.notes,
                    Modifier.weight(columnWeight.component4()),
                    fontSize = 14.sp
                )
                IconButton(
                    onClick = { viewModel.removeSet(workoutLogExerciseState.key,workoutSets.guid) },
                    Modifier
                        .weight(columnWeight.component5())
                        .align(CenterVertically)
                ) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                }
            }

            if (index != workoutLogExerciseState.value.workoutSets.size - 1) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = { viewModel.addSet(workoutLogExerciseState.key) }, Modifier.align(Alignment.End)) {
            Text(text = "Log Set")
        }

    }


}


@Composable
fun WorkoutLogScreenPreview() {

    ToughFitnessAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {


//            WorkoutLogScreen(EmptyDestinationsNavigator, resultRecipient = )
        }
    }

}