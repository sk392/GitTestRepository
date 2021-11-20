package com.example.gittestapplication.ui.model

import com.example.gittestapplication.domain.model.Repository

data class RepositoryUIModel(
    val repositoryId: Int,
    val repositoryUrl: String,
    val repositoryName: String,
    val repositoryFullName: String,
    val userId: Int,
    val userName: String,
    val userUrl: String
)

fun Repository.toUIModel() = RepositoryUIModel(
    repositoryId = id,
    repositoryUrl = url,
    repositoryName = name,
    repositoryFullName = fullName,
    userId = owner.id,
    userName = owner.login,
    userUrl = owner.url
)