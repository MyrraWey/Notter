package com.muravyovdmitr.notter.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Dima Muravyov on 02.08.2016.
 */

public class SnackBar {
    public void showShortSnackBarMessage(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public void showLongSnackBarMessage(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
