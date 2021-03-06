package com.example.gittestapplication.ui.model

import com.example.gittestapplication.domain.model.Repository

data class RepositoryUIModel(
    val repositoryId: Int,
    val repositoryUrl: String,
    val repositoryName: String,
    val userId: Int,
    val userName: String,
    val userUrl: String,
    val updateAt: String,
    val starCount: Int,
    val forkCount: Int
) {
    val tabRepository = Tap.Repository(repositoryUrl)

    sealed class Tap {
        data class Repository(val url: String) : Tap()
    }
}

fun Repository.toUIModel() = RepositoryUIModel(
    repositoryId = id,
    repositoryUrl = url,
    repositoryName = name,
    userId = owner.id,
    userName = owner.login,
    userUrl = owner.url,
    updateAt = updatedAt ?: "",
    starCount = stargazersCount ?: 0,
    forkCount = forksCount ?: 0
)
