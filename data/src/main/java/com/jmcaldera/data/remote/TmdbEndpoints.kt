package com.jmcaldera.data.remote

/**
 * Created by jmcaldera on 08-03-18.
 */
class TmdbEndpoints {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        private const val API_VERSION = "3"
        private const val MOVIE = "/movie"
        const val NOW_PLAYING_MOVIES = API_VERSION + MOVIE + "/now_playing"
        const val UPCOMING_MOVIES = API_VERSION + MOVIE + "/upcoming"
        const val TOP_RATES_MOVIES = API_VERSION + MOVIE + "/top_rated"
        const val POPULAR_MOVIES = API_VERSION + MOVIE + "/popular"

        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"
        const val DEFAULT_POSTER_SIZE = "w500"
        const val POSTER_URL_W500 = BASE_IMAGE_URL + DEFAULT_POSTER_SIZE
    }
}