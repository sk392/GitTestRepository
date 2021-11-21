package com.example.gittestapplication.ui.model

data class RepositoryUIModel(
    val repositoryId: Int,
    val repositoryUrl: String,
    val repositoryName: String,
    val repositoryFullName: String,
    val userId: Int,
    val userName: String,
    val userUrl: String,
    val onClick: (() -> Unit)? = null
)
