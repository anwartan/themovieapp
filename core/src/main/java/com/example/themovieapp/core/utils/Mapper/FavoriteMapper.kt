package com.example.themovieapp.core.utils.Mapper

import com.example.themovieapp.core.data.source.local.entity.FavoriteEntity
import com.example.themovieapp.core.domain.model.Favorite

object FavoriteMapper {
    fun mapEntityToDomain(input: FavoriteEntity) = Favorite(
        movieId = input.movieId,
        createAt = input.createAt
    )
}
