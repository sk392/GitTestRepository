package com.example.gittestapplication.data.model

data class SearchRepositoryResponse(
    val totalCount: Int?,
    val incompleteResults: Boolean?,
    val items: List<RepositoryResponse?>?
)