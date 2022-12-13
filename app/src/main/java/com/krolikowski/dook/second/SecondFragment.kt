package com.krolikowski.dook.second

import androidx.fragment.app.viewModels
import com.krolikowski.dook.base.BaseFragment
import com.krolikowski.dook.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : BaseFragment<FragmentSecondBinding, SecondViewState, SecondViewEvent, SecondViewModel>(
    FragmentSecondBinding::inflate
) {
    override val viewModel: SecondViewModel by viewModels()

    override fun handleViewState(viewState: SecondViewState) {

    }
}