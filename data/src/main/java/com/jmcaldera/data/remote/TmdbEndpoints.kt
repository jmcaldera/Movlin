package com.jmcaldera.data.remote

/**
 * Created by jmcaldera on 08-03-18.
 */
class TmdbEndpoints {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        private const val API_VERSION = "3"
        private const val MOVIE = "/movie"
        private const val PERSON = "/person"
        const val CREDITS = "/credits"
        const val NOW_PLAYING_MOVIES = API_VERSION + MOVIE + "/now_playing"
        const val UPCOMING_MOVIES = API_VERSION + MOVIE + "/upcoming"
        const val TOP_RATES_MOVIES = API_VERSION + MOVIE + "/top_rated"
        const val POPULAR_MOVIES = API_VERSION + MOVIE + "/popular"
        const val DETAILS_MOVIE = API_VERSION + MOVIE + "/"
        const val DETAILS_PERSON = API_VERSION + PERSON + "/"

        const val APPEND_TO_RESPONSE = "append_to_response="
        const val APPEND_IMAGES = "images"
        const val APPEND_VIDEOS = "videos"

        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"

        const val DEFAULT_POSTER_SIZE = "w500"
        const val POSTER_SIZE_W185 = "w185"

        const val BACKDROP_SIZE_W300 = "w300"   // 300x169
        const val BACKDROP_SIZE_W780 = "w780"   // 725x408

        const val PROFILE_SIZE_W45 = "w45"
        const val PROFILE_SIZE_W185 = "w185"

        const val POSTER_URL_W500 = BASE_IMAGE_URL + DEFAULT_POSTER_SIZE
        const val POSTER_URL_W185 = BASE_IMAGE_URL + POSTER_SIZE_W185

        const val BACKDROP_URL_W300 = BASE_IMAGE_URL + BACKDROP_SIZE_W300
        const val BACKDROP_URL_W780 = BASE_IMAGE_URL + BACKDROP_SIZE_W780

        const val PROFILE_URL_W45 = BASE_IMAGE_URL + PROFILE_SIZE_W45
        const val PROFILE_URL_W185 = BASE_IMAGE_URL + PROFILE_SIZE_W185
    }
}