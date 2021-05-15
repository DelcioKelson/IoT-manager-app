package com.manager.login.forgotpassword

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelForgotPasswordFactory(private val dialog: DialogFragment) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelForgotPassword::class.java)) {
            return ViewModelForgotPassword(dialog) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}