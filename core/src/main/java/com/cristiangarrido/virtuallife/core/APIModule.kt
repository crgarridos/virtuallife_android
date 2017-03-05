package com.cristiangarrido.virtuallife.core

import com.cristiangarrido.virtuallife.NetworkModule
import com.cristiangarrido.virtuallife.tutorial.GithubService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = arrayOf(com.cristiangarrido.virtuallife.NetworkModule::class))
class APIModule {

    @AppScope
    @Provides fun gson(): Gson {
        return GsonBuilder().create()
    }

    @AppScope
    @Provides fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
//                .baseUrl("http://httpbin.org")
                .baseUrl("https://api.github.com/")
                .build()
    }

    @AppScope
    @Provides fun githubService(retrofit : Retrofit): com.cristiangarrido.virtuallife.tutorial.GithubService {
        return retrofit.create(com.cristiangarrido.virtuallife.tutorial.GithubService::class.java)
    }
}