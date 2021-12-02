package com.simple.weather.app.android.presentation.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListFragment<A : RecyclerView.Adapter<*>, B : ViewBinding> : BaseFragment<B>() {

    protected abstract val recyclerView: RecyclerView
    private var _adapter: A? = null
    protected val adapter: A
        get() = requireNotNull(_adapter)

    protected abstract fun createAdapter(): A

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    @CallSuper
    protected open fun initList() {
        recyclerView.adapter = createAdapter().also {
            _adapter = it
        }
    }

    override fun onDestroyView() {
        recyclerView.adapter = null
        _adapter = null
        super.onDestroyView()
    }
}