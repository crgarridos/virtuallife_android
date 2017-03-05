package com.cristiangarrido.virtuallife.core

import android.content.Context
import com.cristiangarrido.virtuallife.core.AppScope
import com.jakewharton.picasso.OkHttp3Downloader
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File

/**
 * Created by cristian on 05/03/17.
 */
@Module(includes = arrayOf(ContextModule::class))
class NetworkModule {

    @AppScope
    @Provides fun cacheDir(context: Context): File {
        val cacheFile = File(context.cacheDir, "okhttp_cache")
        cacheFile.mkdirs()
        return cacheFile
    }

    @AppScope
    @Provides fun cache(cacheDir: File) = Cache(cacheDir, 7 * 24 * 3600)

    @AppScope
    @Provides fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor({ message -> Timber.d(message) })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @AppScope
    @Provides fun cacheOfflineInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (true /*isNetworkAvailable()*/) {
                request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
            } else {
                request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
            }
            chain.proceed(request)
        }
    }

    @AppScope
    @Provides fun client(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor,
                         cacheOfflineInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(cacheOfflineInterceptor)
                .cache(cache)
                .build()
    }
}