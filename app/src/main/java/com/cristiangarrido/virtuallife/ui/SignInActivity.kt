package com.cristiangarrido.virtuallife.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.cristiangarrido.virtuallife.R
import com.cristiangarrido.virtuallife.base.BaseActivity
import com.cristiangarrido.virtuallife.extensions.getString
import com.cristiangarrido.virtuallife.extensions.setVisible
import com.cristiangarrido.virtuallife.extensions.testValidity
import com.cristiangarrido.virtuallife.presentation.presenter.SignInPresenter
import com.cristiangarrido.virtuallife.presentation.view.SignInView
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import timber.log.Timber


/**
 * Created by cristian on 12/02/17
 */
class SignInActivity : BaseActivity(), SignInView, GoogleApiClient.OnConnectionFailedListener {


    @InjectPresenter
    lateinit var mSignInPresenter: SignInPresenter

    lateinit var mGoogleApiClient: GoogleApiClient

    var mAuth: FirebaseAuth? = FirebaseAuth.getInstance()

    var mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        val user = firebaseAuth.currentUser
        if (user != null) {
            // User is signed in
            Timber.d("onAuthStateChanged:signed_in:" + user.uid)
        } else {
            // User is signed out
            Timber.d("onAuthStateChanged:signed_out")
        }
        updateUI(user)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()


        signInEmailBtn.setOnClickListener {
            if (emailInput.testValidity(true) && passwordInput.testValidity(true)) {
                val email = emailInput.getString()
                val password = passwordInput.getString()
                mSignInPresenter.signInWithEmail(email, password)
            }
        }

        signInGoogleBtn.setOnClickListener { signIn() }
        logOutBtn.setOnClickListener { signOut() }
        deleteBtn.setOnClickListener { revokeAccess() }

    }

    public override fun onStart() {
        super.onStart()
        mAuth?.addAuthStateListener(mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        mAuth?.removeAuthStateListener(mAuthListener!!)
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Timber.d("onConnectionFailed:" + connectionResult)
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show()
    }


    private val RC_SIGN_IN = 200

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK && data != null) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                // Google Sign In was successful, authenticate with Firebase
                val account = result.signInAccount!!
//                firebaseAuthWithGoogle(account)
            } else {
                Timber.i("Google Sign In failed with status : ${result.status.statusCode}")
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]
                updateUI(null)
                // [END_EXCLUDE]
            }
        }
    }
    // [END onactivityresult]



    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signOut() {
        // Firebase sign out
        mAuth!!.signOut()

        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback { updateUI(null) }
    }

    private fun revokeAccess() {
        // Firebase sign out
        mAuth!!.signOut()

        // Google revoke access
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback { updateUI(null) }
    }

    private fun updateUI(user: FirebaseUser?) {
        hideProgressDialog()

        if (user != null) {
            loginStatusTxt!!.text = user.email
        } else {
            loginStatusTxt!!.text = null
        }
        signInGoogleBtn.setVisible(user == null)
        logOutBtn.setVisible(user != null)
        deleteBtn.setVisible(user != null)
    }

    override fun signInWithGoogle() {
    }

    override fun showLoginSuccessfully() {
        toast("lsask")
    }


}


