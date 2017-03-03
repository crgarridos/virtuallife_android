package com.cristiangarrido.virtuallife.extensions

import com.andreabaccega.widget.FormEditText

/**
 * Created by cristian on 19/02/17.
 */

fun FormEditText.testValidity(focusIfInvalid : Boolean): Boolean {
    val valid = testValidity()
    if(!valid && focusIfInvalid) requestFocus()
    return valid
}