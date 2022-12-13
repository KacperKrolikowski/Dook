package com.krolikowski.dook.first

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.krolikowski.dook.base.BaseFragment
import com.krolikowski.dook.databinding.FragmentFirstBinding
import com.krolikowski.dook.first.adapter.ImagesAdapter
import com.krolikowski.dook.networking.entities.ImageEntity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FirstFragment :
    BaseFragment<FragmentFirstBinding, FirstViewState, FirstViewEvent, FirstViewModel>(
        FragmentFirstBinding::inflate
    ) {
    override val viewModel: FirstViewModel by viewModels()

    private val nasaAdapter = ImagesAdapter(onItemClick = ::onItemClick)

    override fun handleViewState(viewState: FirstViewState) {
        when (viewState) {
            is FirstViewState.Success -> setSuccessState(viewState.images)
            is FirstViewState.Loading -> setLoadingState()
            is FirstViewState.Error -> setErrorState(viewState.error)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onViewEvent(FirstViewEvent.GetImages(3))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.apply {
                adapter = nasaAdapter
                setHasFixedSize (true)
                onFlingListener = null
            }

            picturesNumberPicker.apply {
                doOnTextChanged { text, _, _, count ->
                    if (count > 0) {
                        text.toString().toInt().let { imagesCount ->
                            if (imagesCount > 0 && imagesCount != nasaAdapter.itemCount) viewModel.onViewEvent(
                                FirstViewEvent.GetImages(
                                    imagesCount
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    private fun setSuccessState(imagesList: List<ImageEntity>) {
        binding.apply {
            functionButton.isVisible = false
            picturesNumberPicker.isVisible = true
            progressIndicator.isVisible = false
        }
        setImages(imagesList)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setImages(imagesList: List<ImageEntity>) {
        nasaAdapter.apply {
            images = imagesList
            notifyDataSetChanged()
        }
    }

    private fun onItemClick(nasaItem: ImageEntity) {
        findNavController().navigate(
            FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                nasaItem
            )
        )
    }

    private fun setLoadingState() {
        changeError(false)
        setEmptyList()
        with(binding) {
            progressIndicator.isVisible = true
            picturesNumberPicker.isVisible = false
            functionButton.apply {
                isVisible = true
                text = "Cancel"
                setOnClickListener {
                    viewModel.onViewEvent(FirstViewEvent.CancelRequest)
                }
            }
        }
    }

    private fun setEmptyList(){
        nasaAdapter.apply{
            images = listOf()
            notifyDataSetChanged()
        }
    }

    private fun setErrorState(error: String) {
        Timber.e(error)
        changeError(true)
        setEmptyList()
        with(binding) {
            progressIndicator.isVisible = false
            picturesNumberPicker.isVisible = false
            functionButton.apply {
                isVisible = true
                text = "Retry"
                setOnClickListener {
                    viewModel.onViewEvent(FirstViewEvent.GetImages(3))
                }
            }
        }
    }

    private fun changeError(isError: Boolean) {
        with(binding) {
            errorImage.isVisible = isError
            errorText.apply {
                if (text.isEmpty() && isError) text = "Something went wrong"
                isVisible = isError
            }
        }
    }
}