package com.app.cellapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.cellapp.R
import com.app.cellapp.databinding.ActivityMainBinding
import com.app.cellapp.presentation.CellsViewModel
import com.app.cellapp.ui.list.CellsAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CellsViewModel
    private lateinit var adapter: CellsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.holder = this
        binding.lifecycleOwner = this
        setupToolbar()
        setupObservers()
        adapter = CellsAdapter()
        recyclerView.adapter = adapter
        binding.viewModel = viewModel
    }

    private fun setupToolbar() {
        toolbar.title = ""
        setSupportActionBar(toolbar)
    }

    private fun setupObservers() {
        viewModel = ViewModelProviders.of(this).get(CellsViewModel::class.java)
    }

    fun generateCell() {
        viewModel.generateCell()
    }
}
