package com.manager.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.Patterns


class UtilsFunctions {

    companion object {
        fun isEmailValid(email: String?): Boolean {
            return email!!.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isNetworkConnected(activity: Activity): Boolean {
            val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
        }
    }
}