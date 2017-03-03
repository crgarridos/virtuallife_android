package com.cristiangarrido.virtuallife.extensions

import android.view.View

/**
 * Created by cristian on 19/02/17.
 */

fun View.setVisible(visible: Boolean) {
    visibility = if(visible) View.VISIBLE else View.GONE
}
