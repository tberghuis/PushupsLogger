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
    val createdAt = System.currentTimeMillis()
    viewModelScope.launch {
      val rep = Rep(numRep = numReps.value, createdAt = createdAt)
      repDao.insert(rep)
    }
  }

  // test to see if this works tomorrow
  fun getTodaysTotal(): Flow<Int> {
    val todayStartOfDay = LocalDate.now().atStartOfDay()
    val todayStartOfDayMilli = localDateTimeToMilli(todayStartOfDay)
    return repDao.getTodaysTotal(todayStartOfDayMilli)
  }


  fun getYesterdaysTotal(): Flow<Int> {
    val todayStartOfDay = LocalDate.now().atStartOfDay()
    val yesterdayStartOfDay = todayStartOfDay.minusDays(1)

    val todayStartOfDayMilli = localDateTimeToMilli(todayStartOfDay)
    val yesterdayStartOfDayMilli = localDateTimeToMilli(yesterdayStartOfDay)

    return repDao.getYesterdaysTotal(yesterdayStartOfDayMilli, todayStartOfDayMilli)
  }

  private fun localDateTimeToMilli(localDateTime: LocalDateTime): Long {
    // this was a bitch to figure out
    // need to do a course or read more examples
    val instant = localDateTime.toInstant(ZoneOffset.systemDefault().rules.getOffset(localDateTime))
    return instant.toEpochMilli()
  }

}