package com.example.drinkbrowserapp.ui.common

import android.content.Context
import android.widget.Toast

fun Context.displayToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}