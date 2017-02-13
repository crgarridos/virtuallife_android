package com.cristiangarrido.virtuallife.application

import com.cristiangarrido.virtuallife.base.BaseActivity
import com.cristiangarrido.virtuallife.presenter.SignInPresenter

/**
 * Created by cristian on 12/02/17.
 */
class SignInActivity : BaseActivity() {

    private val mSignInPresenter: SignInPresenter by lazy { SignInPresenter(this) }


}