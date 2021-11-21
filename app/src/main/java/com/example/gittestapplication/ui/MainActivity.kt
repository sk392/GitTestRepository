package com.example.gittestapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gittestapplication.R
import com.example.gittestapplication.databinding.ActivityMainBinding
import com.example.gittestapplication.ui.adapter.MainAdapter
import com.example.gittestapplication.ui.base.SimpleDataBindingPresenter
import com.example.gittestapplication.ui.model.RepositoryUIModel
import com.example.gittestapplication.ui.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by inject()
    private lateinit var mainAdapter: MainAdapter

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
        mainAdapter = MainAdapter(object : SimpleDataBindingPresenter() {
            override fun onClick(view: View, item: Any) {
                when (item) {
                    is RepositoryUIModel.Tap.Repository -> {
                        Toast.makeText(this@MainActivity, "url = ${item.url}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
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

    private fun search(query: String) {
        viewModel.fetch(query)
    }

    private fun initObservers() {
        viewModel.fetchRepositories.observe(this, {
            mainAdapter.submitList(it)
        })
    }
}