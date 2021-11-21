package com.example.gittestapplication.domain.usecase

import com.example.gittestapplication.domain.Result
import com.example.gittestapplication.domain.model.Order
import com.example.gittestapplication.domain.model.Repository
import com.example.gittestapplication.domain.model.Sort
import com.example.gittestapplication.domain.repository.GitSearchRepository

class GitSearchUseCase(
    private val repository: GitSearchRepository
) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 30
        const val DEFAULT_PAGE = 1

        val DEFAULT_SORT = Sort.DEFAULT
        val DEFAULT_ORDER = Order.DESC
    }

    suspend operator fun invoke(
        query: String,
        sort: Sort = DEFAULT_SORT,
        order: Order = DEFAULT_ORDER,
        pageSize: Int = DEFAULT_PAGE_SIZE,
        pageNumber: Int = DEFAULT_PAGE
    ): Result<List<Repository>> {
        return repository.fetchRepositories(query, sort, order, pageSize, pageNumber)
    }
}