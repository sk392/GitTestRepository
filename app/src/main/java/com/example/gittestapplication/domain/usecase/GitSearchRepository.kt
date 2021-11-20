package com.example.gittestapplication.domain.usecase

import com.example.gittestapplication.domain.model.Order
import com.example.gittestapplication.domain.model.Repository
import com.example.gittestapplication.domain.model.Sort

interface GitSearchRepository {
    fun fetchRepositories(query: String, sort: Sort, order: Order, pageSize: Int, pageNumber: Int) : List<Repository>
}