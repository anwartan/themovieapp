package com.example.themovieapp.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation


class MovieFavoriteEntity (


    @Embedded
    val favoriteEntity: FavoriteEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "id"
    )
    val movieEntity: MovieEntity,

)