package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.WorkoutLogExercise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutSetDialog(
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
                            value = weight,
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