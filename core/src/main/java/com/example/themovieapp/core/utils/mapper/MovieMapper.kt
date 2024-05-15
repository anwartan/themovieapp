package com.example.themovieapp.core.utils.mapper

import com.example.themovieapp.core.data.source.local.entity.MovieEntity
import com.example.themovieapp.core.data.source.remote.response.MovieResponse
import com.example.themovieapp.core.domain.model.Movie

object MovieMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                posterPath = it.posterPath,
                popularity = it.popularity,
                backdropPath = it.backdropPath.orEmpty(),
                releaseDate = it.releaseDate,
                adult = it.adult,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,

            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                posterPath = it.posterPath,
                popularity = it.popularity,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                adult = it.adult,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        }


    private fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        video = input.video,
        title = input.title,
        posterPath = input.posterPath,
        popularity = input.popularity,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        adult = input.adult,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount
    )
    fun mapEntityToDomain(input: MovieEntity) = Movie(
        id = input.id,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        video = input.video,
        title = input.title,
        posterPath = input.posterPath,
        popularity = input.popularity,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        adult = input.adult,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount
    )
}