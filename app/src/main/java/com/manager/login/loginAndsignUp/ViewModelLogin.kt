package com.manager.login.loginAndsignUp

import android.content.Intent
import android.text.Editable
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.manager.MainActivity
import com.manager.R
import com.manager.login.forgotpassword.DialogForgotPassword

class ViewModelLogin(private val fragment: Fragment) : ViewModel() {

    private val mAuth: FirebaseAuth = Firebase.auth


    fun isEmailValid(email: String?): Boolean {
        return email!!.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun login(editPassword: EditText, editEmail: EditText) {
        if (isEmailValid(editEmail.text.toString())) {
            if (isPasswordValid(editPassword.text)) {
                editPassword.error = null // Clear the error

                tryLogin(editEmail.text.toString(), editPassword.text.toString())
            } else {
                editPassword.error = fragment.getString(R.string.invalid_password)
            }
        } else {
            editEmail.error = fragment.getString(R.string.invalid_email_address)
        }
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text!!.length >= 8 && text.length <= 16
    }

    private fun tryLogin(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(fragment.requireActivity()) { task: Task<AuthResult?> ->
                    if (task.isSuccessful) {
                        val intent = Intent(fragment.context, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        fragment.startActivity(intent)
                    } else {
                        Toast.makeText(
                                fragment.context,
                                fragment.getString(R.string.wrong_password_mailaddress),
                                Toast.LENGTH_LONG
                        ).show()
                    }
                }
    }

    fun onClickPasswordForgot() {
        val ft: FragmentTransaction = fragment.parentFragmentManager.beginTransaction()
        val dialog = DialogForgotPassword()
        dialog.show(ft, "DialogFragment")
    }
}