package com.dwaynewillmakeit.toughfitnessapp.ui.exercise_list

import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.dwaynewillmakeit.toughfitnessapp.data.local.entity.Exercise
import com.dwaynewillmakeit.toughfitnessapp.ui.theme.ToughFitnessAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.ResultBackNavigator
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination()
fun ExerciseListScreen(
    muscleGroup: String,
    viewModel: ExerciseViewModel = hiltViewModel(),
    resultNavigator: ResultBackNavigator<String>
) {

    val state = viewModel.state

    val selectedExercises = mutableMapOf<Long,Exercise>()

    val TAG = "WorkoutLogScreen: "

    Log.i(TAG,"Encoded: "+ Json.encodeToString(state.exercises))
    Log.i(TAG,"Decoded: "+ Json.decodeFromString<List<ExerciseState>>(Json.encodeToString(state.exercises)).toString())




    viewModel.fetchExercisesByMuscleGroup(muscleGroup);

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxHeight(.9f)
                    .fillMaxWidth()
            ) {

                items(state.exercises.size) { i ->

                    val exercise = state.exercises[i]
                    ExerciseCard(exercise,selectedExercises)
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }

            Button(onClick = { resultNavigator.navigateBack(Json.encodeToString(selectedExercises.map { it.value })) }) {
                Text(text = "Add to my plan")
            }
        }

    }
}

@Composable
private fun ExerciseCard(
    exerciseState: ExerciseState,
    selectedExercises: MutableMap<Long, Exercise>,
) {

    var isSelected by remember { mutableStateOf(false) }

    val exercise = exerciseState.exercise

    if(isSelected){

        if(!selectedExercises.containsKey(exercise.id)){
            selectedExercises[exercise.id] = exercise
        }
    }else{
        if(selectedExercises.containsKey(exercise.id)){
            selectedExercises.remove(exercise.id)
        }

    }

    Log.i("ExerciseList: ",selectedExercises.toString())




    if(exerciseState.isSelected){
        Log.i("ExerciseList","${exercise.name} Selected")
    }else{
        Log.i("ExerciseList","${exercise.name} Not Selected")
    }

    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Card(
        modifier = Modifier
            .height(95.dp)
            .width(345.dp)
            .clickable { isSelected = !isSelected },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .size(74.dp)
                    .border(BorderStroke(1.dp, Color.Black))
            ) {

                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = exercise.imageUrl.replace("http:", "https:"))
                            .apply(block = fun ImageRequest.Builder.() {
                                size(Size.ORIGINAL)
                            }).build(), imageLoader = imageLoader
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
//                Image(
//                    painter = rememberAsyncImagePainter(
//                        exercise.imageUrl,
//                        imageLoader = imageLoader
//                    ),
//                    contentDescription = "", contentScale = ContentScale.Crop,
//                )
            }

            Text(
                text = exercise.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth(0.7f)

            )

            Column() {
                Checkbox(checked = isSelected, onCheckedChange = { isSelected = it })
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "")
                }
            }
        }
    }
}

@Composable
@Preview
fun ExerciseListScreenPreview() {

    ToughFitnessAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
//            ExerciseListScreen(navController)
        }
    }

}