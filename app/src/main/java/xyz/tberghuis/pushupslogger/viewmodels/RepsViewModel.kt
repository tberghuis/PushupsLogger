package xyz.tberghuis.pushupslogger.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import xyz.tberghuis.pushupslogger.data.Rep
import xyz.tberghuis.pushupslogger.data.RepDao
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
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

  // test to see if this works tomorrow
  fun getTodaysTotal(): Flow<Int> {
    val today: LocalDate = LocalDate.now()
    val startOfDay: LocalDateTime = today.atStartOfDay()
    // this was a bitch to figure out
    // need to do a course or read more examples
    val midnight = startOfDay.toInstant(ZoneOffset.systemDefault().rules.getOffset(startOfDay))
    val midnightMilli = midnight.toEpochMilli()

    return repDao.getTodaysTotal(midnightMilli)

  }
}