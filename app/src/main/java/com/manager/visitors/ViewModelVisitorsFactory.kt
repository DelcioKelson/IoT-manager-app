package com.manager.visitors

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelVisitorsFactory(private val fragment: Fragment) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelVisitors::class.java)) {
            return ViewModelVisitors(fragment) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}