package com.krolikowski.dook.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.krolikowski.dook.base.BaseFragment
import com.krolikowski.dook.databinding.FragmentDetailsBinding
import com.krolikowski.dook.extensions.loadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, DetailsViewState, DetailsViewEvent, DetailsViewModel>(
        FragmentDetailsBinding::inflate
    ) {
    override val viewModel: DetailsViewModel by viewModels()

    private val args: DetailsFragmentArgs by navArgs()

    override fun handleViewState(viewState: DetailsViewState) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            image.loadFromUrl(args.nasaObject.imageUrl)
            if (args.nasaObject.copyright.isEmpty()) {
                copyrightText.isVisible = false
                copyrightValue.isVisible = false
            } else {
                copyrightValue.text = args.nasaObject.copyright
            }
            dateValue.text = args.nasaObject.date
            descriptionValue.text = args.nasaObject.description
        }
    }
}