package ru.padawans.database.model.main

import androidx.room.*
import ru.padawans.database.Converters
import ru.padawans.domain.model.main.Dates
import ru.padawans.domain.model.main.MovieGeneralInfo
import ru.padawans.domain.model.main.UpcomingMovies
import java.lang.RuntimeException

//todo make table name via consts
@Entity
@TypeConverters(Converters::class)
class UpcomingMoviesEntity(

    @ColumnInfo(name = "page")
    val page: Int?,
    @ColumnInfo(name = "totalResults")
    val totalResults: Int?,
    @ColumnInfo(name = "totalPages")
    val totalPages: Int?,
    @ColumnInfo(name = "contentType")
    val contentType: String = "",

    ) {

    constructor(upcomingMovies: UpcomingMovies,contentType: String) : this(
        upcomingMovies.page,
        upcomingMovies.totalResults,
        upcomingMovies.totalPages,
        contentType
    ) {
    }


    @PrimaryKey(autoGenerate = true)
    var uid: Int? = null

    @ColumnInfo(name = "moviesId")
    var moviesId: Int = 0

    fun convert(results: List<MovieGeneralInfo>): UpcomingMovies {
        val dates = Dates("", "")
        if (results != null && page != null && totalResults != null && dates != null && totalPages != null) {
            return UpcomingMovies(results, page, totalResults, dates, totalPages)
        } else {
            throw RuntimeException("Not valid parametrs")
        }
    }


}