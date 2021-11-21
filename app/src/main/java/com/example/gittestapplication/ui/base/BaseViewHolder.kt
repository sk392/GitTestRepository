package com.example.gittestapplication.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T, B : ViewDataBinding> private constructor(
    protected val viewBinding: B
) : RecyclerView.ViewHolder(viewBinding.root) {

    constructor(
        @LayoutRes layoutResId: Int,
        parent: ViewGroup
    ) : this(
        DataBindingUtil.inflate<B>(LayoutInflater.from(parent.context), layoutResId, parent, false)
    )

    open fun onBind(item: T) {}
    open fun onViewRecycled() {}

}