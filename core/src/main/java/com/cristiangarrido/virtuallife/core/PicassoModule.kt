package com.cristiangarrido.virtuallife.core

import android.content.Context
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

/**
 * Created by cristian on 05/03/17.
 */
@Module(includes = arrayOf(ContextModule::class, NetworkModule::class))
class PicassoModule {

    @AppScope
    @Provides fun picasso(context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build()
    }

    @AppScope
    @Provides fun okHttp3Dowloader(client: OkHttpClient) = OkHttp3Downloader(client)
}