package com.example.gittestapplication.ui.adapter

import android.view.ViewGroup
import com.example.gittestapplication.R
import com.example.gittestapplication.databinding.ItemMainRepositoryBinding
import com.example.gittestapplication.ui.base.DataBindingPresenter
import com.example.gittestapplication.ui.base.BaseViewHolder
import com.example.gittestapplication.ui.model.RepositoryUIModel

class MainRepositoryItemViewHolder(
    parent: ViewGroup
) : BaseViewHolder<RepositoryUIModel, ItemMainRepositoryBinding>(
    R.layout.item_main_repository,
    parent
) {

    override fun onBind(item: RepositoryUIModel, presenter: DataBindingPresenter) {
        viewBinding.vm = item
        viewBinding.presenter = presenter
    }

    override fun onViewRecycled() {
        //e.g. clear image
    }
}