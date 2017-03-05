package com.cristiangarrido.virtuallife.core

import android.content.Context
import com.cristiangarrido.virtuallife.core.AppScope
import dagger.Module
import dagger.Provides

/**
 * Created by cristian on 05/03/17.
 */

@Module
class ContextModule(private val context: Context) {
    @AppScope
    @Provides fun context() = context
}