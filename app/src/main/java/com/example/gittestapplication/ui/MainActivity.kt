package com.example.gittestapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gittestapplication.R
import com.example.gittestapplication.databinding.ActivityMainBinding
import com.example.gittestapplication.domain.model.Order
import com.example.gittestapplication.domain.model.Sort
import com.example.gittestapplication.ui.adapter.MainAdapter
import com.example.gittestapplication.ui.base.SimpleDataBindingPresenter
import com.example.gittestapplication.ui.model.RepositoryUIModel
import com.example.gittestapplication.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
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

        binding.spOrder.apply {
            val items = Order.values()
            adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, items)

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    mainViewModel.updateFilter(order = Order.values()[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        binding.spPageSize.apply {
            val items = resources.getStringArray(R.array.page_size_array)
            adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, items)

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    mainViewModel.updateFilter(pageSize = Integer.valueOf(items[position]))
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        binding.spSort.apply {
            val items = Sort.values()
            adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, items)
            setSelection(Sort.DEFAULT.ordinal)

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    mainViewModel.updateFilter(sort = Sort.values()[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        binding.rvResult.apply {
            itemAnimator = null
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }

        binding.btSearch.apply {
            setOnClickListener {
                search(binding.etSearch.text.toString(), true)
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
            }
        }
    }

    private fun search(query: String, force: Boolean = false) {
        mainViewModel.fetch(query, force)
    }

    private fun initObservers() {
        mainViewModel.fetchRepositories.observe(this, {
            mainAdapter.submitList(it)
        })
        mainViewModel.showErrorMessage.observe(this, {
            Toast.makeText(this, "error = $it", Toast.LENGTH_SHORT).show()
        })
        mainViewModel.isLoading.observe(this, {
            if (it) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        })
    }

    private fun showProgressBar() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progress.visibility = View.GONE
    }
}