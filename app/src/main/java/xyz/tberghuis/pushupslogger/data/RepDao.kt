package xyz.tberghuis.pushupslogger.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface RepDao {


  @Insert
  suspend fun insert(rep: Rep)


  @Query("SELECT TOTAL(numRep) FROM rep WHERE createdAt >= :todayStartOfDay")
  fun getTodaysTotal(todayStartOfDay: Long): Flow<Int>

  @Query(
    """SELECT TOTAL(numRep) FROM rep WHERE createdAt >= :yesterdayStartOfDay 
    AND createdAt < :todayStartOfDay"""
  )
  fun getYesterdaysTotal(yesterdayStartOfDay: Long, todayStartOfDay: Long): Flow<Int>

}