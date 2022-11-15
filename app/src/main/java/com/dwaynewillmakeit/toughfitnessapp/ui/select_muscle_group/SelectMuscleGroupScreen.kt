package com.dwaynewillmakeit.toughfitnessapp.ui.select_muscle_group

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.dwaynewillmakeit.toughfitnessapp.R
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise
import com.dwaynewillmakeit.toughfitnessapp.ui.destinations.ExerciseListScreenDestination
import com.dwaynewillmakeit.toughfitnessapp.ui.theme.ToughFitnessAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultBackNavigator
import com.ramcosta.composedestinations.result.ResultRecipient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun SelectMuscleGroupScreen(
    navigator: DestinationsNavigator,
    resultRecipient: ResultRecipient<ExerciseListScreenDestination, String>,
    resultNavigator: ResultBackNavigator<String>
) {

    resultRecipient.onNavResult { result ->
        when (result) {
            is NavResult.Canceled -> {
                // `GoToProfileConfirmationDestination` was shown but it was canceled
                // and no value was set (example: dialog/bottom sheet dismissed)
            }
            is NavResult.Value -> {
               Log.i("Select Muscle: ",result.value)

                resultNavigator.navigateBack(result.value)
            }
        }
    }


    Scaffold {

        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                Row(modifier = Modifier.padding(bottom = 16.dp)) {
                    MuscleGroupCard(R.drawable.ic_arms, "Arms", "Icon Arms", "arm", navigator)
                    Spacer(modifier = Modifier.width(16.dp))
                    MuscleGroupCard(R.drawable.ic_back, "Back", "Icon Back", "back", navigator)
                }
            }
            item {
                Row(modifier = Modifier.padding(bottom = 16.dp)) {
                    MuscleGroupCard(R.drawable.ic_chest, "Chest", "Icon Chest", "chest", navigator)
                    Spacer(modifier = Modifier.width(16.dp))
                    MuscleGroupCard(R.drawable.ic_abs, "Core", "Icon Abdominal", "core", navigator)
                }
            }
            item {
                Row(modifier = Modifier.padding(bottom = 16.dp)) {
                    MuscleGroupCard(
                        R.drawable.ic_legs,
                        "Legs & Glutes",
                        "Icon Legs",
                        "legs & glutes",
                        navigator
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    MuscleGroupCard(
                        R.drawable.ic_shoulders,
                        "Shoulders",
                        "Icon Shoulders",
                        "shoulders",
                        navigator
                    )
                }
            }
        }
    }
}

@Composable
private fun MuscleGroupCard(
    @DrawableRes image: Int,
    title: String,
    imageDescription: String,
    muscleGroup: String,
    navigator: DestinationsNavigator
) {
    Card(
        modifier = Modifier
            .size(135.dp)
            .clickable { navigator.navigate(ExerciseListScreenDestination(muscleGroup)) },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = imageDescription,
                modifier = Modifier.size(90.dp)
            )


            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Composable
@Preview
fun SelectMuscleGroupScreenPreview() {

    ToughFitnessAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
//            SelectMuscleGroupScreen()
        }
    }

}