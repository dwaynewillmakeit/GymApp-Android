package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExerciseLog(
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
        mutableStateOf(workoutLogExerciseState.value.lastSetCount)
    }


    var workOutSetUUID by remember {
        mutableStateOf("")
    }



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
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .weight(.9f)
                .padding(top = 12.dp, bottom = 8.dp),
            fontSize = 20.sp,

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
                fontWeight = FontWeight.Light, textAlign = TextAlign.Center
            )
            Text(
                text = "Weight",
                Modifier.weight(columnWeight.component2()),
                fontWeight = FontWeight.Light, textAlign = TextAlign.Center
            )
            Text(
                text = "Rep",
                Modifier.weight(columnWeight.component3()),
                fontWeight = FontWeight.Light, textAlign = TextAlign.Center
            )
            Text(
                text = "Notes",
                Modifier.weight(columnWeight.component4()),
                fontWeight = FontWeight.Light,
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


        WorkoutSetsLog(
            workoutLogExerciseState = workoutLogExerciseState,
            onWeightChange = { weight = it },
            onRepCountChange = { repCount = it },
            onNotesChange = { notes = it },
            onWorkOutSetUUIDChange = { workOutSetUUID = it },
            onToggleDialog = { openDialog = !openDialog },
            onRemoveWorkoutSet = { exerciseLogUUID, workoutLogUUID ->
                viewModel.removeWorkoutSet(
                    exerciseLogUUID,
                    workoutLogUUID
                )
                setCount--
            },
            columnWeight = columnWeight
        )


        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = {
                repCount = ""
                weight = ""
                notes = ""
                workOutSetUUID = ""
                openDialog = !openDialog
            },
            Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(
                text = "Log Set",
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
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

                if (workOutSetUUID.isEmpty()) {

                    viewModel.addWorkoutSet(
                        workoutLogExerciseState.key,
                        weight.toFloat(),
                        repCount = repCount.toInt(),
                        notes = notes,
                        ++setCount
                    )
                } else {
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

@Composable
private fun WorkoutSetsLog(
    onWeightChange: (String) -> Unit,
    onRepCountChange: (String) -> Unit,
    onNotesChange: (String) -> Unit,
    onWorkOutSetUUIDChange: (String) -> Unit,
    onToggleDialog: () -> Unit,
    columnWeight: List<Float>,
    workoutLogExerciseState: Map.Entry<String, WorkoutLogExerciseState>,
    onRemoveWorkoutSet: (String, String) -> Unit
) {

    val workoutSets = workoutLogExerciseState.value.workoutSets.values


    workoutSets.sortedBy { it.set }.forEachIndexed { index, workoutSet ->

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
            onWeightChange(workoutSet.weight.toString())
            onRepCountChange(workoutSet.repCount.toString())
            onNotesChange(workoutSet.notes)
            onWorkOutSetUUIDChange(workoutSet.guid)
            onToggleDialog()
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
                    onRemoveWorkoutSet(workoutLogExerciseState.key, workoutSet.guid)

                },
                Modifier
                    .weight(columnWeight.component5())
                    .align(Alignment.CenterVertically)
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
}