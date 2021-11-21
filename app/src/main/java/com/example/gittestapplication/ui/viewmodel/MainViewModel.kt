package com.example.gittestapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gittestapplication.domain.Result
import com.example.gittestapplication.domain.model.Order
import com.example.gittestapplication.domain.model.Sort
import com.example.gittestapplication.domain.usecase.GitSearchUseCase
import com.example.gittestapplication.ui.model.RepositoryUIModel
import com.example.gittestapplication.ui.model.toUIModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val searchUseCase: GitSearchUseCase
) : ViewModel() {
    companion object {
        private const val DEFAULT_PAGE_SIZE = 30
        private const val DEFAULT_PAGE = 1
        private const val FETCH_MORE_VISIBLE_THRESHOLD = 6

        private val DEFAULT_SORT = Sort.DEFAULT
        private val DEFAULT_ORDER = Order.DESC
    }

    private val _fetchRepositories = MutableLiveData<List<RepositoryUIModel>>()
    val fetchRepositories: LiveData<List<RepositoryUIModel>> = _fetchRepositories

    private val _showErrorMessage = MutableLiveData<String>()
    val showErrorMessage: LiveData<String> = _showErrorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var currentPageNumber: Int = DEFAULT_PAGE
    private var currentSort: Sort = DEFAULT_SORT
    private var currentOrder: Order = DEFAULT_ORDER
    private var currentPageSize: Int = DEFAULT_PAGE_SIZE
    private var currentQuery: String = ""


    fun fetch(
        query: String = currentQuery,
        force: Boolean = false
    ) {
        if (query.isEmpty()) return
        if (force) {
            currentPageNumber = DEFAULT_PAGE
        }
        currentQuery = query
        _isLoading.value = true

        viewModelScope.launch {
            val result = searchUseCase(currentQuery, currentSort, currentOrder, currentPageSize, currentPageNumber)
            _isLoading.value = false

            when (result) {
                is Result.Success -> {
                    _fetchRepositories.value = result.value.map { it.toUIModel() }
                }
                is Result.Failure -> {
                    _showErrorMessage.value = result.throwable.message
                }
            }
        }
    }

    fun fetchMore(visiblePosition: Int) {
        if (_isLoading.value == true || _fetchRepositories.value.isNullOrEmpty()) return

        if (fetchRepositories.value?.size ?: 0 < visiblePosition + FETCH_MORE_VISIBLE_THRESHOLD) {
            _isLoading.value = true
            currentPageNumber++

            viewModelScope.launch {
                val result = searchUseCase(currentQuery, currentSort, currentOrder, currentPageSize, currentPageNumber)
                _isLoading.value = false

                when (result) {
                    is Result.Success -> {
                        val addedList = result.value.map { it.toUIModel() }
                        _fetchRepositories.value = (_fetchRepositories.value?.toMutableList() ?: emptyList()) + addedList
                    }
                    is Result.Failure -> {
                        _showErrorMessage.value = result.throwable.message
                    }
                }
            }
        }
    }

    fun updateFilter(order: Order = currentOrder, sort: Sort = currentSort, pageSize: Int = currentPageSize) {
        currentOrder = order
        currentSort = sort
        currentPageSize = pageSize

        fetch(force = true)
    }
}