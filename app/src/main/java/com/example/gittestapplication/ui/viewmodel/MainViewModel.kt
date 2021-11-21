package com.example.gittestapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gittestapplication.domain.model.Repository
import com.example.gittestapplication.ui.model.RepositoryUIModel
import com.example.gittestapplication.ui.model.toUIModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _fetchRepositories = MutableLiveData<List<RepositoryUIModel>>()
    val fetchRepositories: LiveData<List<RepositoryUIModel>> = _fetchRepositories

    fun fetch(query: String) {
        viewModelScope.launch {
            val result = fetchRepositoriesUseCase((query))

            _fetchRepositories.value = result
        }
    }

    private suspend fun fetchRepositoriesUseCase(query: String): List<RepositoryUIModel> {
        delay(1000)
        return mutableListOf<RepositoryUIModel>().apply {
            for (i in 0..100) {
                add(Repository(i).toUIModel())
            }
        }
    }
}