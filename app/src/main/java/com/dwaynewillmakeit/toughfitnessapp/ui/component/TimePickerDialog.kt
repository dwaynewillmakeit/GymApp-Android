package com.dwaynewillmakeit.toughfitnessapp.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import java.time.LocalTime

@Composable
fun TimePickerDialog(dialogState: MaterialDialogState,onTimeChanged:(LocalTime)->Unit){
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        timepicker(
            colors = TimePickerDefaults.colors(
                inactiveBackgroundColor = MaterialTheme.colorScheme.onBackground.copy(
                    .03F
                ),
                activeBackgroundColor =  MaterialTheme.colorScheme.primary.copy(.78f),
                selectorColor = MaterialTheme.colorScheme.primary,
                borderColor = Color.Transparent,


                )
        ) { time ->
            onTimeChanged(time)
        }
    }

    DatePickerDefaults.colors()
}