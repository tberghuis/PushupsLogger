package xyz.tberghuis.pushupslogger.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.tberghuis.pushupslogger.viewmodels.RepsViewModel
import android.util.StatsLog.logEvent
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.collectAsState
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun RepsScreen(

//  viewModel: RepsViewModel = hiltViewModel(),
  viewModel: RepsViewModel = hiltViewModel(),

  ) {

  // to put in vm
  val numReps = remember { mutableStateOf("") }
  val todaysTotal = viewModel.repDao.getTodaysTotal().collectAsState(initial = 0)


  Column {
    Row {
      Text("todays total: ")
//      Text(if (todaysTotal.value == null) "null" else "not null")
      Text(todaysTotal.value.toString())
    }


    TextField(
      value = numReps.value,
      onValueChange = { numReps.value = it }
    )


    Button(onClick = {
      Log.d("xxx", "button clicked")
      viewModel.insertReps(numReps.value)
    }) {
      Text("log reps")
    }

    TmpButton()
  }
}

@Composable
fun TmpButton() {
  Button(onClick = {
    val date = Date(1634083654409)
    val formatter: DateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("Australia/NSW")
    val dateFormatted: String = formatter.format(date)
    Log.d("xxx", "date formatted $dateFormatted")
  }) {
    Text("tmp button")
  }
}
