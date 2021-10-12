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
  private val repDao: RepDao
) : ViewModel() {

  fun insertReps() {
    viewModelScope.launch {
      val rep = Rep(numRep = 1, createdAt = 1)
      repDao.insert(rep)
    }
  }
}