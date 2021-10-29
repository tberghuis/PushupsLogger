package xyz.tberghuis.pushupslogger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import dagger.hilt.android.AndroidEntryPoint
import xyz.tberghuis.pushupslogger.screens.RepsScreen
import xyz.tberghuis.pushupslogger.ui.theme.PushupsLoggerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private val TAG = "MainActivity"
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)


    logIntent(intent)

    setContent {
      PushupsLoggerTheme {
        Surface(color = MaterialTheme.colors.background) {
          RepsScreen()
        }
      }
    }
  }


  fun logIntent(intent: Intent) {
    val bundle: Bundle = intent.extras ?: return

    Log.d(TAG, "======= logIntent ========= %s")
    Log.d(TAG, "Logging intent data start")

    bundle.keySet().forEach { key ->
      Log.d(TAG, "[$key=${bundle.get(key)}]");
    }

    Log.d(TAG, "Logging intent data complete")
  }


}
