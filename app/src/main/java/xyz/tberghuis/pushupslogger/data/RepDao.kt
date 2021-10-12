package xyz.tberghuis.pushupslogger.data

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface RepDao {


  @Insert
  suspend fun insert(rep: Rep)

}