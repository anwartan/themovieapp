package com.example.themovieapp.core.data.source.local.entity

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "favorite")
class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val movieId: Int,
    @Nullable
    val createAt: Date?
)