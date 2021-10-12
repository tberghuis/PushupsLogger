package xyz.tberghuis.pushupslogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import xyz.tberghuis.pushupslogger.screens.RepsScreen
import xyz.tberghuis.pushupslogger.ui.theme.PushupsLoggerTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PushupsLoggerTheme {
        Surface(color = MaterialTheme.colors.background) {
          RepsScreen()
        }
      }
    }
  }
}

//@Composable
//fun Greeting(name: String) {
//  Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//  PushupsLoggerTheme {
//    Greeting("Android")
//  }
//}