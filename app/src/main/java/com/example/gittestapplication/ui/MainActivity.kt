package com.example.gittestapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gittestapplication.R
import com.example.gittestapplication.databinding.ActivityMainBinding
import com.example.gittestapplication.ui.adapter.MainAdapter
import com.example.gittestapplication.ui.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by inject()
    private val mainAdapter: MainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        initView()
        initObservers()
    }

    private fun initView() {
        binding.rvResult.apply {
            itemAnimator = null
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }

        binding.btSearch.apply {
            setOnClickListener {
                search(binding.etSearch.text.toString())
            }
        }
    }

    private fun search(query: String){
        viewModel.fetch(query)
    }

    private fun initObservers() {
        viewModel.fetchRepositories.observe(this, {
            mainAdapter.submitList(it)
        })
    }
}