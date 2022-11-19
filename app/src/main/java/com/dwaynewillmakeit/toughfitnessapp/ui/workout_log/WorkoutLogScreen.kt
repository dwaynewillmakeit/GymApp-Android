package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise
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

    var openDialog by remember {
        mutableStateOf(false)
    }

    var weight by remember {
        mutableStateOf("")
    }

    var repCount by remember {
        mutableStateOf("")
    }

    var notes by remember {
        mutableStateOf("")
    }

    var setCount by remember {
        mutableStateOf(1)
    }

    var workOutSetUUID by remember {
        mutableStateOf("")
    }


    val workoutSets = workoutLogExerciseState.value.workoutSets.values

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


        workoutSets.sortedBy { it.set }.forEachIndexed { index, workoutSet ->

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
                weight = workoutSet.weight.toString()
                repCount = workoutSet.repCount.toString()
                notes = workoutSet.notes
                workOutSetUUID = workoutSet.guid
                openDialog = true
            }) {

                Text(
                    text = workoutSet.set.toString(),
                    Modifier.weight(columnWeight.component1()),
                    textAlign = TextAlign.Center, fontSize = 14.sp
                )
                Text(
                    text = "${workoutSet.weight}lbs",
                    Modifier
                        .weight(columnWeight.component2()),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
                Text(
                    text = "${workoutSet.repCount}",
                    Modifier.weight(columnWeight.component3()),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
                Text(
                    text = workoutSet.notes,
                    Modifier.weight(columnWeight.component4()),
                    fontSize = 14.sp
                )
                IconButton(
                    onClick = {
                        viewModel.removeWorkoutSet(
                            workoutLogExerciseState.key,
                            workoutSet.guid
                        )

                        setCount--
                    },
                    Modifier
                        .weight(columnWeight.component5())
                        .align(CenterVertically)
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "")
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
        Button(onClick = {
            repCount = ""
            weight = ""
            notes = ""
            workOutSetUUID = ""
            openDialog = !openDialog
        }, Modifier.align(Alignment.End)) {
            Text(text = "Log Set")
        }

        WorkoutSetDialog(
            openDialog,
            onDialog = { openDialog = !openDialog },
            workoutLogExerciseState.value.workoutLogExercise,
            weight,
            onWeightChanged = {
                weight = it
            },
            repCount = repCount,
            onRepCountChanged = { repCount = it },
            notes = notes,
            onNotesChanged = { notes = it },
            onSaveClicked = {

                if(workOutSetUUID.isEmpty()){

                viewModel.addWorkoutSet(
                    workoutLogExerciseState.key,
                    weight.toFloat(),
                    repCount = repCount.toInt(),
                    notes = notes,
                    setCount++
                )
                }else{
                    viewModel.modifyWorkoutSet(
                        workoutLogExerciseState.key,
                        workOutSetUUID,
                        weight.toFloat(),
                        repCount = repCount.toInt(),
                        notes = notes
                    )
                }
                openDialog = false

            }
        )


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WorkoutSetDialog(
    openDialog: Boolean,
    onDialog: () -> Unit,
    workoutLogExercise: WorkoutLogExercise,
    weight: String,
    onWeightChanged: (String) -> Unit,
    notes: String,
    onNotesChanged: (String) -> Unit,
    repCount: String,
    onRepCountChanged: (String) -> Unit,
    onSaveClicked: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = onDialog, title = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = workoutLogExercise.exercise_name,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Log Set Information",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = weight.toString(),
                            onValueChange = onWeightChanged,
                            Modifier.weight(.4f),
                            label = {
                                Text("Weight lbs", style = MaterialTheme.typography.bodySmall)
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            )
                        )
                        Spacer(modifier = Modifier.weight(.1f))
                        OutlinedTextField(
                            value = repCount,
                            onValueChange = onRepCountChanged,
                            Modifier.weight(.4F),
                            label = {
                                Text("Reps", style = MaterialTheme.typography.bodySmall)
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = notes,
                        onValueChange = onNotesChanged,
                        Modifier.fillMaxWidth(),
                        label = {
                            Text("Notes", style = MaterialTheme.typography.bodySmall)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                            capitalization = KeyboardCapitalization.Sentences, autoCorrect = true
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(onClick = onSaveClicked) {
                        Text(text = "Save")
                    }
                }
            },
            confirmButton = {

            },
            dismissButton = {

            })

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



