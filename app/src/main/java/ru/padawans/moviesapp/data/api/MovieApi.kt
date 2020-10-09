package ru.padawans.moviesapp.data.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.padawans.moviesapp.data.model.trending.Dto.TrendingMoviesDto
import ru.padawans.moviesapp.data.model.upcoming.Dto.UpcomingMoviesDto

interface MovieApi {

    @GET("3/movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey:String,
        @Query("language") language:String,
        @Query("page") pageCount:Int
    ):Single<UpcomingMoviesDto>

    @GET("3/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language:String,
        @Query("page") pageCount:Int
    ):Single<UpcomingMoviesDto>

    @GET("3/trending/movie/week")
    fun getTrendingMovie(
        @Query("api_key") apiKey: String,
        @Query("page") pageCount:Int
    ):Single<TrendingMoviesDto>

}