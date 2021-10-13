package xyz.tberghuis.pushupslogger.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import xyz.tberghuis.pushupslogger.data.Rep
import xyz.tberghuis.pushupslogger.data.RepDao
import javax.inject.Inject

@HiltViewModel
class RepsViewModel @Inject constructor(
  val repDao: RepDao
) : ViewModel() {

  // todo default value
  val numReps = mutableStateOf(5)

  fun insertReps() {

    try {

      val createdAt = System.currentTimeMillis()
      viewModelScope.launch {
        val rep = Rep(numRep = numReps.value, createdAt = createdAt)
        repDao.insert(rep)
      }


    } catch (e: Exception) {

    }


  }
}