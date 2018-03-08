package com.jmcaldera.data

import com.jmcaldera.data.TmdbEndpoints.Companion.NOW_PLAYING_MOVIES
import com.jmcaldera.data.TmdbEndpoints.Companion.POPULAR_MOVIES
import com.jmcaldera.data.TmdbEndpoints.Companion.TOP_RATES_MOVIES
import com.jmcaldera.data.TmdbEndpoints.Companion.UPCOMING_MOVIES
import com.jmcaldera.data.model.*
import retrofit2.Call
import retrofit2.http.GET

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
}