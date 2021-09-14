package com.simple.weather.app.android.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import org.kodein.di.DIAware

abstract class BaseFragment<B : ViewBinding> : Fragment(), DIAware {

    override val di by lazy { (requireContext().applicationContext as DIAware).di }

    private var _binding: B? = null
    protected val binding: B
        get() = requireNotNull(_binding)

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}