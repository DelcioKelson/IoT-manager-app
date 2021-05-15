package com.manager.login.forgotpassword

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.manager.databinding.DialogForgotPasswordBinding
import com.manager.login.forgotpassword.ViewModelForgotPassword
import com.manager.login.forgotpassword.ViewModelForgotPasswordFactory


class DialogForgotPassword : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Inflate the layout for this fragment
        val inflater = requireActivity().layoutInflater
        val binding = DialogForgotPasswordBinding.inflate(inflater)
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setContentView(binding.root)

        val viewModelForgotPasswordFactory = ViewModelForgotPasswordFactory(this)
        val viewModelForgotPassword = ViewModelProvider(this, viewModelForgotPasswordFactory).get(
            ViewModelForgotPassword::class.java
        )
        binding.viewModelForgotPassword = viewModelForgotPassword

        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            it.window?.setLayout(width, height)
        }
    }
}