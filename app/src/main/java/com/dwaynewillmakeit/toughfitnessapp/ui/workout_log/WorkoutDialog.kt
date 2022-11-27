package com.dwaynewillmakeit.toughfitnessapp.ui.workout_log

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun WorkoutDialog(
    openDialog: Boolean = false,
    onDialog: () -> Unit,
    onSaveClicked: () -> Unit,
    title: String,
    bodyText: String,
    confirmBtnText:String = "Confirm",
    dismissButtonText:String = "Dismiss"
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = onDialog, title = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = bodyText,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            text = {

            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onSaveClicked()
                        onDialog()
                    }
                ) {
                    Text(confirmBtnText)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDialog
                ) {
                    Text(dismissButtonText)
                }
            })

    }
}