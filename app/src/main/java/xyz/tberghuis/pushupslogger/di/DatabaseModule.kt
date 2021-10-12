package xyz.tberghuis.pushupslogger.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import xyz.tberghuis.pushupslogger.data.AppDatabase
import xyz.tberghuis.pushupslogger.data.RepDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
    return Room.databaseBuilder(
      appContext,
      AppDatabase::class.java,
      "rep.db"
    ).build()
  }

  @Provides
  fun provideRepDao(database: AppDatabase): RepDao {
    return database.repDao()
  }
}