package com.example.themovieapp.core.utils.mapper

import com.example.themovieapp.core.data.source.local.entity.DetailEntity
import com.example.themovieapp.core.domain.model.Detail

object DetailMapper {
    fun mapEntityToDomain(input: DetailEntity) = Detail(
        movieId = input.movieId,
        createAt = input.createAt,
        isFavorite = input.isFavorite,
        isWatch = input.isWatch
    )
}
