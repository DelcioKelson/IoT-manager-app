package com.manager.utils

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseUser


@BindingAdapter("setPhoto", "saveBtn")
fun setPhoto(imageView: ImageView, imageUri: Uri?, button: MaterialButton) {
    if (imageUri != null) {
        Glide.with(imageView.context).load(imageUri).into(imageView)
        button.visibility = MaterialButton.VISIBLE
    }
}

@BindingAdapter("setInitialPhoto")
fun setInitialPhoto(imageView: ImageView, user: FirebaseUser) {

    try {
        val uri = Uri.parse(user.photoUrl.toString())
        Glide.with(imageView.context).load(uri).into(imageView)
    } catch (e: Exception) {

    }
}

@BindingAdapter("onClickGoogleSign")
fun bindSignInClick(button: Button, method: () -> Unit) {
    button.setOnClickListener { method.invoke() }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setHourPrice")
fun setHourPrice(textView: TextView, prices:HashMap<String, Double?>){

    val o: Any? = prices["60"]
    textView.text = o.toString()

}