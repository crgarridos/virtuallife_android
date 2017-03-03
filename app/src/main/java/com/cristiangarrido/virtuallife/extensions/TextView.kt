package com.cristiangarrido.virtuallife.extensions

import android.widget.TextView

/**
 * Created by cristian on 19/02/17.
 */
fun TextView.getString(): String {
    return text?.toString() ?: ""
}