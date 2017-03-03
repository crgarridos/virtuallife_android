package com.cristiangarrido.virtuallife.tutorial

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by cristian on 03/03/17.
 */
interface GithubService {

    @GET("users/{username}/repos")
    fun getUserRepos(@Path("username") username : String) : Call<List<GithubRepo>>
}

data class GithubRepo(
        val name : String,
        val description : String,
        val html_url : String
) {

}
