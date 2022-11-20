package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dwaynewillmakeit.toughfitnessapp.R
import com.dwaynewillmakeit.toughfitnessapp.ui.component.DatePickerDialog
import com.dwaynewillmakeit.toughfitnessapp.ui.component.TimePickerDialog
import com.dwaynewillmakeit.toughfitnessapp.ui.destinations.SelectMuscleGroupScreenDestination
import com.dwaynewillmakeit.toughfitnessapp.ui.theme.ToughFitnessAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.time.format.DateTimeFormatter

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

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()




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
                .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
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
                            value = state.startDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")),
                            onValueChange = {},
                            label = { Text("Start Date") },
                            placeholder = {
                                Text(
                                    text = "Start Date", style = MaterialTheme.typography.bodySmall
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth(.5f)
                                .clickable { dateDialogState.show() },
                            enabled = false,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "workoutDate"
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledBorderColor = MaterialTheme.colorScheme.onSurface,
                                disabledLabelColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        OutlinedTextField(
                            value = viewModel.state.startTime.format(DateTimeFormatter.ofPattern("hh:mm a")),
                            onValueChange = {},
                            label = { Text("Start Time") },
                            placeholder = {
                                Text(
                                    text = "Start Time", style = MaterialTheme.typography.bodySmall
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .clickable { timeDialogState.show() },
                            enabled = false,
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_clock_24),
                                    contentDescription = "workoutTime"
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledBorderColor = MaterialTheme.colorScheme.onSurface,
                                disabledLabelColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }

                    OutlinedTextField(
                        value = state.notes,
                        onValueChange = { viewModel.setNotes(it) },
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

                        ExerciseLog(
                            workoutLogExercise, viewModel
                        )

                    }


                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }

    DatePickerDialog(dialogState = dateDialogState, onDateChange = { viewModel.setStartDate(it) })

    TimePickerDialog(dialogState = timeDialogState, onTimeChanged = { viewModel.setStartTime(it) })
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



