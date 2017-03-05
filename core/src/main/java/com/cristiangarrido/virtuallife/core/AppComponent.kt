package com.cristiangarrido.virtuallife.core

import com.squareup.picasso.Picasso
import dagger.Component

/**
 * Created by cristian on 05/03/17
 */

@AppScope
@Component(modules = arrayOf(PicassoModule::class, APIModule::class))
interface AppComponent {
    fun getPicasso() : Picasso
    fun getGithubService() : GithubService
}