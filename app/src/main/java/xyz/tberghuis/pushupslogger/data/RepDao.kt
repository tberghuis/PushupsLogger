package xyz.tberghuis.pushupslogger.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface RepDao {


  @Insert
  suspend fun insert(rep: Rep)


  @Query("SELECT TOTAL(numRep) FROM rep WHERE createdAt >= :midnight")
  fun getTodaysTotal(midnight: Long): Flow<Int>

}