package com.example.gittestapplication.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.gittestapplication.ui.model.RepositoryUIModel

class MainAdapter : ListAdapter<RepositoryUIModel, MainRepositoryItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRepositoryItemViewHolder {
        return MainRepositoryItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MainRepositoryItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onViewRecycled(holder: MainRepositoryItemViewHolder) {
        holder.onViewRecycled()
    }

    private class DiffCallback : DiffUtil.ItemCallback<RepositoryUIModel>() {
        override fun areItemsTheSame(oldItem: RepositoryUIModel, newItem: RepositoryUIModel): Boolean {
            return oldItem.repositoryId == newItem.repositoryId
        }

        override fun areContentsTheSame(oldItem: RepositoryUIModel, newItem: RepositoryUIModel): Boolean {
            return oldItem == newItem
        }
    }

}
