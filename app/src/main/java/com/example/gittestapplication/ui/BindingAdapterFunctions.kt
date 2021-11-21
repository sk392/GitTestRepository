package com.example.gittestapplication.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.gittestapplication.R
import com.example.gittestapplication.ui.model.RepositoryUIModel

object BindingAdapterFunctions {

    @JvmStatic
    @BindingAdapter("extra_info")
    fun setExtraInfo(view: TextView, model: RepositoryUIModel) {
        val stringFormat = view.context.getString(R.string.repository_extra_info)

        view.text = String.format(stringFormat, model.forkCount, model.starCount)
    }
}