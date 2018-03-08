package com.jmcaldera.data

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by jmcaldera on 08-03-18.
 */
class TmdbInterceptor(private val apiKey: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain?.request()

        val url = request?.url()!!.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()

        return chain.proceed(
                request.newBuilder()
                .url(url)
                .build()
        )
    }
}