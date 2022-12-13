package com.krolikowski.dook.first

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.krolikowski.dook.base.BaseFragment
import com.krolikowski.dook.databinding.FragmentFirstBinding
import com.krolikowski.dook.first.adapter.ImagesAdapter
import com.krolikowski.dook.networking.entities.ImageEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding, FirstViewState, FirstViewEvent, FirstViewModel>(
    FragmentFirstBinding::inflate
) {
    override val viewModel: FirstViewModel by viewModels()

    override fun handleViewState(viewState: FirstViewState) {
        when (viewState) {
            is FirstViewState.Success -> setImages(viewState.images)
            else -> setLoadigState()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun setImages(images: List<ImageEntity>){
        binding.recyclerView.adapter = ImagesAdapter(images)
    }

    private fun setLoadigState() {}
}