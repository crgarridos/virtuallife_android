package com.cristiangarrido.virtuallife.presentation.view

import com.arellomobile.mvp.MvpView

/**
 * Created by cristian on 12/02/17.
 */
interface SignInView : MvpView{
//    fun signIn()
//    fun signOut()
//    fun signUp()
    fun signInWithGoogle()
//    fun signInWithFacebook()
//    fun signInWithTwitter()

    fun showLoginSuccessfully()
}