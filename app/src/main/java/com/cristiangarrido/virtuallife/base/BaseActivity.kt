package com.cristiangarrido.virtuallife.base

import android.app.ProgressDialog
import android.content.Intent
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AppCompatActivity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.cristiangarrido.virtuallife.R
import com.cristiangarrido.virtuallife.VirtualLifeApp

/**
 * Created by cristian on 10/02/17
 */
open class BaseActivity : MvpAppCompatActivity() {

    @VisibleForTesting
    var mProgressDialog: ProgressDialog? = null

    fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog!!.setMessage(getString(R.string.loading))
            mProgressDialog!!.isIndeterminate = true
        }

        mProgressDialog!!.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    public override fun onStop() {
        super.onStop()
        hideProgressDialog()
    }

    fun <T> startActivity(activity : Class<T> ){
    }

    fun getVirtualLifeApp() = application as VirtualLifeApp

}