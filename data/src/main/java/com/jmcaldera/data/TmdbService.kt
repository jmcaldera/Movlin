package com.jmcaldera.data

import com.jmcaldera.data.TmdbEndpoints.Companion.NOW_PLAYING_MOVIES
import com.jmcaldera.data.TmdbEndpoints.Companion.POPULAR_MOVIES
import com.jmcaldera.data.TmdbEndpoints.Companion.TOP_RATES_MOVIES
import com.jmcaldera.data.TmdbEndpoints.Companion.UPCOMING_MOVIES
import com.jmcaldera.data.model.TmdbNowPlaying
import com.jmcaldera.data.model.TmdbPopular
import com.jmcaldera.data.model.TmdbTopRated
import com.jmcaldera.data.model.TmdbUpcoming
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by jmcaldera on 08-03-18.
 */
interface TmdbService {

    @GET(NOW_PLAYING_MOVIES)
    fun getNowPlayingMovies(): Call<TmdbNowPlaying>

    @GET(UPCOMING_MOVIES)
    fun getUpcomingMovies(): Call<TmdbUpcoming>

    @GET(TOP_RATES_MOVIES)
    fun getTopRatedMovies(): Call<TmdbTopRated>

    @GET(POPULAR_MOVIES)
    fun getPopularMovies(): Call<TmdbPopular>
}