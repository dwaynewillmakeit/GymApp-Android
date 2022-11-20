package com.dwaynewillmakeit.toughfitnessapp.ui.component


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.LocalDate

@Composable
fun DatePickerDialog(
    dialogState: MaterialDialogState,
    onDateChange: (LocalDate) -> Unit
) {

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker(colors = DatePickerDefaults.colors(headerBackgroundColor = MaterialTheme.colorScheme.primary.copy(.78f))) { date ->
            onDateChange(date)

        }
    }
}