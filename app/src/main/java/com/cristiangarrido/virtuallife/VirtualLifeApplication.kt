package com.cristiangarrido.virtuallife

import android.app.Application
import com.cristiangarrido.virtuallife.base.BaseActivity
import com.google.gson.GsonBuilder
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File

/**
 * Created by cristian on 03/03/17.
 */
class VirtualLifeApplication : Application() {


    companion object {
        fun get(activity: BaseActivity) = activity.application as VirtualLifeApplication
    }

    lateinit var retrofit: Retrofit
    private set

    override fun onCreate() {
        super.onCreate()


        val gson = GsonBuilder().create()

        Timber.plant(Timber.DebugTree())

        val cacheFile = File(cacheDir, "okhttp_cache")
        cacheFile.mkdirs()

        val httpLoggingInterceptor = HttpLoggingInterceptor({ message -> Timber.d(message)})
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(object : Interceptor{
                    override fun intercept(chain: Interceptor.Chain): Response {
                        var request = chain.request()
                        if (true /*isNetworkAvailable()*/) {
                            request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
                        } else {
                            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                        }
                        return chain.proceed(request)
                    }
                })
                .cache(Cache(cacheFile, 7 * 24 * 3600))
                .build()




        val picasso = Picasso.Builder(this)
                .downloader(OkHttp3Downloader(okHttpClient))
                .build()

//        Picasso.setSingletonInstance(picasso)//Don't do this. Isn't proper Dependency Injection

        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
//                .baseUrl("http://httpbin.org")
                .baseUrl("https://api.github.com/")
                .build()

    }
}