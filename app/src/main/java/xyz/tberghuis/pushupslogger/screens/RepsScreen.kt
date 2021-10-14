package xyz.tberghuis.pushupslogger.screens

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.tberghuis.pushupslogger.viewmodels.RepsViewModel
import android.widget.NumberPicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ncorti.slidetoact.SlideToActView

@Composable
fun RepsScreen(
  viewModel: RepsViewModel = hiltViewModel(),
) {
  val todaysTotal = viewModel.getTodaysTotal().collectAsState(initial = 0)
  val yesterdaysTotal = viewModel.getYesterdaysTotal().collectAsState(initial = 0)

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly
  ) {
    Row {
      Text("yesterdays total: ")
      Text(yesterdaysTotal.value.toString())
    }
    Row {
      Text("todays total: ")
      Text(todaysTotal.value.toString())
    }
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceEvenly
    ) {
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
  }
}


@Composable
fun NumberPickerWrapper(viewModel: RepsViewModel = hiltViewModel()) {
  AndroidView(
    factory = { context ->
      NumberPicker(context).apply {
        setOnValueChangedListener { picker, oldVal, newVal ->
          viewModel.numReps.value = newVal
        }
        minValue = 1
        maxValue = 10
        value = viewModel.numReps.value
        wrapSelectorWheel = false
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

  // i don't know how this works but it does
  val primaryThemeColor = MaterialTheme.colors.primary.hashCode()

  AndroidView(factory = { context ->
    SlideToActView(context).apply {
      text = "Log Reps"
      outerColor = primaryThemeColor
      iconColor = primaryThemeColor
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