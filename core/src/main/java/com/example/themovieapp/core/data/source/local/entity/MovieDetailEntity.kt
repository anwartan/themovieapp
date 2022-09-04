package com.example.themovieapp.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation


class MovieDetailEntity (


    @Embedded
    val detailEntity: DetailEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "id"
    )
    val movieEntity: MovieEntity,

    )