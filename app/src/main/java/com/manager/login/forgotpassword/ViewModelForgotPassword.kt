package com.manager.login.forgotpassword

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.manager.R
import com.manager.utils.UtilsFunctions

class ViewModelForgotPassword(private val dialog: DialogFragment) : ViewModel() {

    fun onClickBack() {
                    dialog.dismiss()
    }

    fun onClickSendEmail(email: EditText) {
        if (UtilsFunctions.isEmailValid(email.text.toString())) {
            Firebase.auth.sendPasswordResetEmail(email.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            MaterialAlertDialogBuilder(dialog.requireActivity())
                                    .setTitle(dialog.getString(R.string.almost_done))
                                    .setMessage(dialog.getString(R.string.sent_with_success))
                                    .setPositiveButton(dialog.getString(R.string.okay)) { _, _ -> }
                                    .show()
                        } else {
                            Toast.makeText(dialog.requireContext(), dialog.getString(R.string.email_does_not_exist), Toast.LENGTH_LONG).show()
                        }
                    }
        }
    }

}