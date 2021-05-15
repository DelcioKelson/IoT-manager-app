package com.manager.visitors

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.manager.R
import kotlinx.coroutines.launch
import com.manager.utils.FirebaseKeeperService
import com.manager.login.forgotpassword.DialogForgotPassword

class ViewModelVisitors (private val fragment: Fragment) : ViewModel() {

    private val users = MutableLiveData<List<ClassUser>>()

    init {
        viewModelScope.launch {
            users.value = FirebaseKeeperService.getUserData()
        }
    }

    fun getGyms(): LiveData<List<ClassUser>> = users

    fun openCreateDialog() {
        val ft: FragmentTransaction = fragment.parentFragmentManager.beginTransaction()
        val dialog = DialogCreateUser()
        dialog.show(ft, "DialogFragment")
    }

    fun onClickSettings() {
        Log.i("mainActivity", "passei")


        Navigation.findNavController(fragment.requireView())
            .navigate(R.id.action_fragmentVisitors_to_fragment_settings)
    }

}
