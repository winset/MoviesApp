package ru.padawans.moviesapp.data

import android.util.Log
import androidx.room.withTransaction
import kotlinx.coroutines.*
import ru.padawans.moviesapp.data.database.MovieDatabase
import ru.padawans.moviesapp.data.model.upcoming.UpcomingMovies
import ru.padawans.moviesapp.data.model.upcoming.db.MovieGeneralInfoEntity
import ru.padawans.moviesapp.data.model.upcoming.db.UpcomingMoviesEntity

class Storage(
    private val database: MovieDatabase
) {

    private val movieDao = database.getMovieForMain()


    suspend fun insertUpcomingMovies(
        upcomingMoviesEntity: UpcomingMoviesEntity,
        movieGeneralInfoEntitys: List<MovieGeneralInfoEntity>
    ) {
        database.withTransaction {
            movieGeneralInfoEntitys.forEach {
                upcomingMoviesEntity.moviesId = it.id
                movieDao.insertUpcomingMovies(upcomingMoviesEntity)
            }
            movieDao.insertMovieGeneralInfo(movieGeneralInfoEntitys)

        }
    }


    fun getMovieGeneralInfo(contentType: String, page: Int): List<MovieGeneralInfoEntity> {
        val upcomingEntity: List<MovieGeneralInfoEntity> =
            movieDao.getUpcomingByTypeAndPage(contentType, page)

        return upcomingEntity
    }

    suspend fun clear(contentType: String, page: Int, database: List<MovieGeneralInfoEntity>) {
        movieDao.clearUpcomingMovies(contentType, page)
        database.forEach { movieDao.clearMovieGeneralInfo(it.id) }
    }
}