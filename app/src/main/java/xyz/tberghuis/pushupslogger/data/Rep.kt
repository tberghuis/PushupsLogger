package xyz.tberghuis.pushupslogger.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rep(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  val numRep: Int,
  val createdAt: Long
)

