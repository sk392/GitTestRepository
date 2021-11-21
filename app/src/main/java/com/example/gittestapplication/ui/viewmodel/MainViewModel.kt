package com.example.gittestapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gittestapplication.domain.model.Repository
import com.example.gittestapplication.ui.model.RepositoryUIModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _fetchRepositories = MutableLiveData<List<RepositoryUIModel>>()
    val fetchRepositories: LiveData<List<RepositoryUIModel>> = _fetchRepositories

    fun fetch(query: String) {
        viewModelScope.launch {
            _fetchRepositories.postValue(fetchRepositoriesUseCase((query)))
        }
    }

    private suspend fun fetchRepositoriesUseCase(query: String): List<RepositoryUIModel> {
        delay(1000)
        return mutableListOf<RepositoryUIModel>().apply {
            for (i in 0..100) {
                add(createRepositoryUIModel(Repository(i)))
            }
        }
    }

    private fun createRepositoryUIModel(model: Repository): RepositoryUIModel {
        return with(model) {
            RepositoryUIModel(
                repositoryId = id,
                repositoryUrl = url,
                repositoryName = name,
                repositoryFullName = fullName,
                userId = owner.id,
                userName = owner.login,
                userUrl = owner.url
            ) {
                Log.e("lattelog", "MainViewModel - toUIModel: tetett")
            }
        }
    }
}