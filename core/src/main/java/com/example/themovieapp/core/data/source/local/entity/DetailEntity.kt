package com.example.themovieapp.core.data.source.local.entity

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "detail")
class DetailEntity(
    @PrimaryKey(autoGenerate = true)
    val movieId: Int,
    val isFavorite: Boolean,
    val isWatch:Boolean,
    @Nullable
    val createAt: Date?
)