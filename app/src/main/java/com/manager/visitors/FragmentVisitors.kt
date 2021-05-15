package com.manager.visitors

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.manager.databinding.FragmentVisitorsBinding


class FragmentVisitors : Fragment() {

    private lateinit var binding: FragmentVisitorsBinding
    private lateinit var visitorsAdapter: AdapterVisitorsList
    private lateinit var userList: List<ClassUser>
    private lateinit var viewModelVisitors: ViewModelVisitors


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Log.i("mainActivity", "passei2")

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        binding = FragmentVisitorsBinding.inflate(inflater, container, false)
        visitorsAdapter = AdapterVisitorsList()
        binding.rvVisitors.adapter = visitorsAdapter

        val viewModelGymsFactory = ViewModelVisitorsFactory(this)
        viewModelVisitors = ViewModelProvider(this, viewModelGymsFactory).get(ViewModelVisitors::class.java)
        binding.visitorsViewModel = viewModelVisitors

        viewModelVisitors.getGyms().observe(viewLifecycleOwner, {
            userList = it
            visitorsAdapter.userList = it
            visitorsAdapter.notifyDataSetChanged()
        })




        return binding.root
    }



 }
