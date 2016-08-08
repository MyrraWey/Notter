package com.muravyovdmitr.notter.utils

import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by Dima Muravyov on 02.08.2016.
 */

class SnackBar {
    fun showShortSnackBarMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    fun showLongSnackBarMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}
