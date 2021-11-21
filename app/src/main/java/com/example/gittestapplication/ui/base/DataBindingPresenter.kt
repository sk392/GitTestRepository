package com.example.gittestapplication.ui.base

import android.view.View

interface DataBindingPresenter {
    fun onClick(view: View, item: Any)
    fun onLongClick(view: View, item: Any)
}

open class SimpleDataBindingPresenter : DataBindingPresenter {
    override fun onClick(view: View, item: Any) {}

    override fun onLongClick(view: View, item: Any) {}
}