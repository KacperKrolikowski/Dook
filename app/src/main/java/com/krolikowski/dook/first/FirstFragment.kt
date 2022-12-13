package com.krolikowski.dook.first

import androidx.fragment.app.viewModels
import com.krolikowski.dook.base.BaseFragment
import com.krolikowski.dook.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding, FirstViewState, FirstViewEvent, FirstViewModel>(
    FragmentFirstBinding::inflate
) {
    override val viewModel: FirstViewModel by viewModels()

    override fun handleViewState(viewState: FirstViewState) {

    }
}