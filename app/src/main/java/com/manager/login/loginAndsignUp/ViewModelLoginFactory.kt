package com.manager.login.loginAndsignUp

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelLoginFactory(private val fragment: Fragment) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelLogin::class.java)) {
            return ViewModelLogin(fragment) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}