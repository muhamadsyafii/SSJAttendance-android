package com.ransyadev.ssjattendance.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.ransyadev.ssjattendance.BuildConfig
import com.ransyadev.ssjattendance.SSJApp
import com.ransyadev.ssjattendance.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkUtil {

    @Provides
    @Singleton
    fun getOkhttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httBuilder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)

        httBuilder.apply {
            if (BuildConfig.DEBUG){
                addInterceptor(loggingInterceptor)
                addInterceptor(getChuckerInterceptor())
            }
        }
        return httBuilder.build()
    }

    @Provides
    fun getRetrofit(): Retrofit {
        val converterFactory = GsonConverterFactory.create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkhttpClient())
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun getChuckerInterceptor(): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(SSJApp.context).build()
    }
}
