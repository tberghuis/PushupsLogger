package xyz.tberghuis.pushupslogger.screens

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.tberghuis.pushupslogger.viewmodels.RepsViewModel
import android.widget.NumberPicker
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ncorti.slidetoact.SlideToActView
import java.time.*

@Composable
fun RepsScreen(
  viewModel: RepsViewModel = hiltViewModel(),
) {
  val todaysTotal = viewModel.getTodaysTotal().collectAsState(initial = 0)

  Column(
    modifier = Modifier
//      .background(Color(0xFF7BB661))
      .fillMaxSize()
      .padding(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Row {
      Text("todays total: ")
      Text(todaysTotal.value.toString())
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
      Button(onClick = {
        if (viewModel.numReps.value > 1)
          viewModel.numReps.value--
      }) {
        Text("-")
      }
      NumberPickerWrapper()
      Button(onClick = {
        if (viewModel.numReps.value < 10)
          viewModel.numReps.value++
      }) {
        Text("+")
      }
    }

    SlideToActViewWrapper()

    Tmp2Button()
  }
}


@Composable
fun NumberPickerWrapper(viewModel: RepsViewModel = hiltViewModel()) {
  AndroidView(
//    modifier = Modifier.fillMaxWidth(),
    factory = { context ->
      NumberPicker(context).apply {
        setOnValueChangedListener { picker, oldVal, newVal ->
          viewModel.numReps.value = newVal
        }
        minValue = 1
        maxValue = 10
        value = viewModel.numReps.value
      }
    },
    update = {
      // todo animate the update
      it.value = viewModel.numReps.value
    }
  )
}

@Composable
fun SlideToActViewWrapper(viewModel: RepsViewModel = hiltViewModel()) {
  AndroidView(factory = { context ->
    SlideToActView(context).apply {
      text = "Log Reps"

      // i thought there would be a more kotliny way to do this???
      // could be because the var is nullable???
      onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
        override fun onSlideComplete(view: SlideToActView) {
          viewModel.insertReps()
          view.resetSlider()
        }
      }

    }
  })
}




//////////////////////////////// tmp code below

@Composable
fun Tmp2Button() {
  Button(onClick = {
    val today: LocalDate = LocalDate.now()
    val startOfDay: LocalDateTime = today.atStartOfDay()
    val midnight = startOfDay.toInstant(ZoneOffset.systemDefault().rules.getOffset(startOfDay))
    val midnightMilli = midnight.toEpochMilli()

    Log.d("xxx", "midnightMilli $midnightMilli ${System.currentTimeMillis()}")
  }) {
    Text("tmp2 button")
  }
}

//@Composable
//fun TmpButton() {
//  Button(onClick = {
//    val date = Date(1634083200000)
//    val formatter: DateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
//    formatter.timeZone = TimeZone.getTimeZone("Australia/NSW")
//    val dateFormatted: String = formatter.format(date)
//    Log.d("xxx", "date formatted $dateFormatted")
//  }) {
//    Text("tmp button")
//  }
//}