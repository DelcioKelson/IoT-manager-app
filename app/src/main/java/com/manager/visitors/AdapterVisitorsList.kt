package com.manager.visitors

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.manager.databinding.CardVisitorsBinding

class AdapterVisitorsList : RecyclerView.Adapter<AdapterVisitorsList.VisitorsHolder>() {

    lateinit var parentContext : Context
    var userList: List<ClassUser> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitorsHolder {
        val inflater = LayoutInflater.from(parent.context)
        parentContext = parent.context
        val binding: CardVisitorsBinding = CardVisitorsBinding.inflate(inflater, parent, false)
        return VisitorsHolder(binding)
    }

    override fun onBindViewHolder(holder: VisitorsHolder, position: Int) {
        holder.binding.user = userList[position]
        holder.binding.cardUser.setOnClickListener{
            MaterialAlertDialogBuilder(parentContext)
                .setTitle("Remover usuario")
                .setMessage("Deseja remover este usuario?")
                .setNegativeButton("Cancelar") { dialog, which ->

                }
                .setPositiveButton("Continuar") { dialog, which ->
                    Firebase.firestore.collection("cities").document("DC")
                        .delete()
                        .addOnSuccessListener { Log.d("card_visitor", "DocumentSnapshot successfully deleted!") }
                        .addOnFailureListener { e -> Log.w("card_visitor", "Error deleting document", e) }                }
                .show()
        }
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class VisitorsHolder(val binding: CardVisitorsBinding) : RecyclerView.ViewHolder(binding.root)
}