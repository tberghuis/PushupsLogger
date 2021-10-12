package xyz.tberghuis.pushupslogger.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.tberghuis.pushupslogger.viewmodels.RepsViewModel

@Composable
fun RepsScreen(

//  viewModel: RepsViewModel = hiltViewModel(),
  viewModel: RepsViewModel = hiltViewModel(),

  ) {
  Text("reps screen")
  Column {
    Button(onClick = {
      Log.d("xxx", "button clicked")
      viewModel.insertReps()
    }) {
      Text("button")
    }
  }
}