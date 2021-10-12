package xyz.tberghuis.pushupslogger.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
  entities = [Rep::class], version = 1, exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun repDao(): RepDao
}