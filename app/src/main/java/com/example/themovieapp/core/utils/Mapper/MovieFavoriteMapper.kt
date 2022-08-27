package com.example.themovieapp.core.utils.Mapper

import com.example.themovieapp.core.data.source.local.entity.MovieFavoriteEntity
import com.example.themovieapp.core.domain.model.MovieFavorite

object MovieFavoriteMapper {
    fun mapEntitiesToDomain(input: List<MovieFavoriteEntity>): List<MovieFavorite> {
        return input.map {
            mapEntityToDomain(it)
        }
    }

    fun mapEntityToDomain(input:MovieFavoriteEntity):MovieFavorite{
        return MovieFavorite(
            movie = MovieMapper.mapEntityToDomain(input.movieEntity),
            favorite = FavoriteMapper.mapEntityToDomain(input.favoriteEntity)
        )
    }

}