package com.manager

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.manager.grant.DialogFragmentGrant

class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null
    private var mAppBarConfiguration: AppBarConfiguration? = null

    @SuppressLint("StaticFieldLeak")
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = Firebase.auth
        currentUser = mAuth!!.currentUser

        beginSession()

    }

    private fun beginSession() {
        navController = Navigation.findNavController(this, R.id.container)
        if (currentUser != null) {
            setupSession()
        } else {
            navController?.setGraph(R.navigation.nav_login)
        }
    }

    private fun setupSession() {


        navController?.setGraph(R.navigation.nav_main)


        val database = Firebase.database

        val myRef = database.getReference("user")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.value

                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                val dialog = DialogFragmentGrant(value.toString())
                dialog.show(ft, "DialogFragment")

                Log.d("mainActivity", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("mainActivity", "Failed to read value.", error.toException())
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        return (NavigationUI.navigateUp(navController!!, mAppBarConfiguration!!)
                || super.onSupportNavigateUp())
    }
}