/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.busschedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.busschedule.databinding.StopScheduleFragmentBinding
import com.example.busschedule.viewmodels.BusScheduleViewModel
import com.example.busschedule.viewmodels.BusScheduleViewModelFactory
// missing import
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StopScheduleFragment: Fragment() {

    companion object {
        var STOP_NAME = "stopName"
    }

    private var _binding: StopScheduleFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var stopName: String

    // getting reference to the view model
    // do not fucking ask me about syntax
    private val viewModel: BusScheduleViewModel by activityViewModels {
        BusScheduleViewModelFactory((activity?.application as BusScheduleApplication).database.scheduleDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            stopName = it.getString(STOP_NAME).toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = StopScheduleFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // setting the recycler view
        recyclerView = binding.recyclerView
        // assigning layout manager to the recycler view
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // assign the adapter property
        val busStopAdapter = BusStopAdapter {}
        recyclerView.adapter = busStopAdapter

        // update a list view -> calling submitList() and passing in the list of bus  stops form the view model
        /**
            * GlobalScope.launch(Dispatchers.IO) {
            *   busStopAdapter.submitList(viewModel.scheduleForStopName(stopName))
            * }
        */
        lifecycle.coroutineScope.launch {
            viewModel.scheduleForStopName(stopName).collect() { busStopAdapter.submitList(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
