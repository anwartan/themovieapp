package com.example.themovieapp.core.utils.mapper

import com.example.themovieapp.core.data.source.local.entity.MovieDetailEntity
import com.example.themovieapp.core.domain.model.MovieDetail

object MovieDetailMapper {
    fun mapEntitiesToDomain(input: List<MovieDetailEntity>): List<MovieDetail> {
        return input.map {
            mapEntityToDomain(it)
        }
    }

    fun mapEntityToDomain(input:MovieDetailEntity):MovieDetail{
        return MovieDetail(
            movie = MovieMapper.mapEntityToDomain(input.movieEntity),
            detail = DetailMapper.mapEntityToDomain(input.detailEntity)
        )
    }

}