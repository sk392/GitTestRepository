package com.example.gittestapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gittestapplication.domain.Result
import com.example.gittestapplication.domain.usecase.GitSearchUseCase
import com.example.gittestapplication.ui.model.RepositoryUIModel
import com.example.gittestapplication.ui.model.toUIModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val searchUseCase: GitSearchUseCase
) : ViewModel() {

    private val _fetchRepositories = MutableLiveData<List<RepositoryUIModel>>()
    val fetchRepositories: LiveData<List<RepositoryUIModel>> = _fetchRepositories

    private val _showErrorMessage = MutableLiveData<String>()
    val showErrorMessage: LiveData<String> = _showErrorMessage

    fun fetch(query: String) {
        viewModelScope.launch {
            when (val result = searchUseCase(query)) {
                is Result.Success -> {
                    _fetchRepositories.value = result.value.map { it.toUIModel() }
                }
                is Result.Failure -> {
                    _showErrorMessage.value = result.throwable.message
                }
            }
        }
    }
}