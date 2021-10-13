package xyz.tberghuis.pushupslogger.viewmodels

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

  fun insertReps(numReps: String) {

    try {
      val numRepsInt = numReps.toInt()
      val createdAt = System.currentTimeMillis()
      viewModelScope.launch {
        val rep = Rep(numRep = numRepsInt, createdAt = createdAt)
        repDao.insert(rep)
      }


    } catch (e: Exception) {

    }


  }
}