package com.cristiangarrido.virtuallife.presentation.presenter

import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.cristiangarrido.virtuallife.model.SignInGoogleTask
import com.cristiangarrido.virtuallife.presentation.view.SignInView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.GoogleAuthProvider
import timber.log.Timber

/**
 * Created by cristian on 12/02/17
 */
@InjectViewState
class SignInPresenter : MvpPresenter<SignInView>(){


    val google = SignInGoogleTask()

    fun signInWithEmail(email: String, password: String) {
        viewState.showLoginSuccessfully()
    }

    fun signInWithGoogle(){
        // [START auth_with_google]
//        private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
//            Timber.d("firebaseAuthWithGoogle:" + acct.id!!)
//            // [START_EXCLUDE silent]
//            viewState.showProgressDialog()
//            // [END_EXCLUDE]
//
//            val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
//            mAuth!!.signInWithCredential(credential)
//                    .addOnCompleteListener(this) { task ->
//                        Timber.d("signInWithCredential:onComplete:" + task.isSuccessful)
//
//                        // If sign in fails, display a message to the user. If sign in succeeds
//                        // the auth state listener will be notified and logic to handle the
//                        // signed in user can be handled in the listener.
//                        if (!task.isSuccessful) {
//                            Timber.w("signInWithCredential", task.exception)
//                            Toast.makeText(this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show()
//                        }
//                        // [START_EXCLUDE]
//                        hideProgressDialog()
//                        // [END_EXCLUDE]
//                    }
//        }
    }

}