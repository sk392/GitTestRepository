package com.example.gittestapplication.domain.repository

import com.example.gittestapplication.domain.Result
import com.example.gittestapplication.domain.model.Order
import com.example.gittestapplication.domain.model.Repository
import com.example.gittestapplication.domain.model.Sort

interface GitSearchRepository {
    suspend fun fetchRepositories(query: String, sort: Sort, order: Order, pageSize: Int, pageNumber: Int) : Result<List<Repository>>
}