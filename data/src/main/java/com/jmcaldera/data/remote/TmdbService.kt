package com.jmcaldera.data.remote

import com.jmcaldera.data.remote.TmdbEndpoints.Companion.NOW_PLAYING_MOVIES
import com.jmcaldera.data.remote.TmdbEndpoints.Companion.POPULAR_MOVIES
import com.jmcaldera.data.remote.TmdbEndpoints.Companion.TOP_RATES_MOVIES
import com.jmcaldera.data.remote.TmdbEndpoints.Companion.UPCOMING_MOVIES
import com.jmcaldera.data.model.*
import com.jmcaldera.data.remote.TmdbEndpoints.Companion.APPEND_IMAGES
import com.jmcaldera.data.remote.TmdbEndpoints.Companion.APPEND_TO_RESPONSE
import com.jmcaldera.data.remote.TmdbEndpoints.Companion.CREDITS
import com.jmcaldera.data.remote.TmdbEndpoints.Companion.DETAILS_MOVIE
import com.jmcaldera.data.remote.TmdbEndpoints.Companion.DETAILS_PERSON
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by jmcaldera on 08-03-18.
 */
interface TmdbService {

    @GET(NOW_PLAYING_MOVIES)
    fun getNowPlayingMovies(): Call<TmdbNowPlayingUpcoming>

    @GET(UPCOMING_MOVIES)
    fun getUpcomingMovies(): Call<TmdbNowPlayingUpcoming>

    @GET(TOP_RATES_MOVIES)
    fun getTopRatedMovies(): Call<TmdbTopRatedPopular>

    @GET(POPULAR_MOVIES)
    fun getPopularMovies(): Call<TmdbTopRatedPopular>

    @GET("$DETAILS_MOVIE{id}?$APPEND_TO_RESPONSE$APPEND_IMAGES")
    fun getMovieDetails(@Path("id") movieId: Int): Call<TmdbMovieDetails>

    @GET("$DETAILS_MOVIE{id}$CREDITS")
    fun getMovieCredits(@Path("id") movieId: Int): Call<TmdbCredits>

    @GET("$DETAILS_PERSON{id}")
    fun getPersonDetails(@Path("id") personId: Int): Call<TmdbPerson>
}