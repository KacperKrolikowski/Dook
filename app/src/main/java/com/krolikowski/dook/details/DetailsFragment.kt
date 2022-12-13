package com.krolikowski.dook.details

import androidx.fragment.app.viewModels
import com.krolikowski.dook.base.BaseFragment
import com.krolikowski.dook.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding, DetailsViewState, DetailsViewEvent, DetailsViewModel>(
    FragmentDetailsBinding::inflate
) {
    override val viewModel: DetailsViewModel by viewModels()

    override fun handleViewState(viewState: DetailsViewState) {

    }
}