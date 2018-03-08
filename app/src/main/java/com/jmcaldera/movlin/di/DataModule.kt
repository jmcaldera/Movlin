package com.jmcaldera.movlin.di

import android.content.Context
import com.jmcaldera.data.remote.TmdbEndpoints
import com.jmcaldera.data.remote.TmdbInterceptor
import com.jmcaldera.data.remote.TmdbService
import com.jmcaldera.movlin.BuildConfig
import com.jmcaldera.movlin.R
import com.jmcaldera.movlin.di.qualifier.ApiKey
import com.jmcaldera.movlin.di.qualifier.ApplicationQualifier
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by jmcaldera on 08-03-18.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    @ApiKey
    fun provideTmdbApiKey(@ApplicationQualifier context: Context): String =
            context.getString(R.string.the_movie_db_api_key)

    @Provides
    @Singleton
    fun provideTmdbInterceptor(@ApiKey apiKey: String): Interceptor = TmdbInterceptor(apiKey)

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
            OkHttpClient().newBuilder()
                    .addInterceptor(interceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.RETROFIT_LOG) Level.BODY else Level.NONE
                    })
                    .build()

    @Provides
    @Singleton
    fun provideApiServiceAdapter(client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(TmdbEndpoints.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    @Provides
    @Singleton
    fun provideTmdbService(retrofit: Retrofit): TmdbService = retrofit.create(TmdbService::class.java)

}