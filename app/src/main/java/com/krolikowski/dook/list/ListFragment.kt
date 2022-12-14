package com.krolikowski.dook.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.get
import com.krolikowski.dook.R
import com.krolikowski.dook.base.BaseFragment
import com.krolikowski.dook.databinding.FragmentListBinding
import com.krolikowski.dook.list.adapter.ImagesAdapter
import com.krolikowski.dook.networking.entities.ImageEntity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ListFragment :
    BaseFragment<FragmentListBinding, ListViewState, ListViewEvent, ListViewModel>(
        FragmentListBinding::inflate
    ) {
    override val viewModel: ListViewModel by viewModels()

    private val nasaAdapter = ImagesAdapter(onItemClick = ::onItemClick)

    override fun handleViewState(viewState: ListViewState) {
        when (viewState) {
            is ListViewState.Success -> setSuccessState(viewState.images)
            is ListViewState.Loading -> setLoadingState()
            is ListViewState.Error -> setErrorState(viewState.error)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onViewEvent(ListViewEvent.GetImages(3))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.apply {
                adapter = nasaAdapter
                setHasFixedSize (true)
                onFlingListener = null
            }

            imagesCountNumberPicker.apply {
                doOnTextChanged { text, _, _, count ->
                    if (count > 0) {
                        text.toString().toInt().let { imagesCount ->
                            if (imagesCount > 0 && imagesCount != nasaAdapter.itemCount) viewModel.onViewEvent(
                                ListViewEvent.GetImages(
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
            imagesCountNumberPicker.isVisible = true
            imagesCountLabel.isVisible = true
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
        findNavController().apply {
            graph[R.id.details_fragment].label = nasaItem.title
            navigate(
                ListFragmentDirections.actionFirstFragmentToSecondFragment(
                    nasaItem
                )
            )
        }
    }

    private fun setLoadingState() {
        changeError(false)
        setEmptyList()
        with(binding) {
            progressIndicator.isVisible = true
            imagesCountNumberPicker.isVisible = false
            imagesCountLabel.isVisible = false
            functionButton.apply {
                isVisible = true
                text = "Cancel"
                setOnClickListener {
                    viewModel.onViewEvent(ListViewEvent.CancelRequest)
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
            imagesCountNumberPicker.isVisible = false
            imagesCountLabel.isVisible = false
            functionButton.apply {
                isVisible = true
                text = "Retry"
                setOnClickListener {
                    viewModel.onViewEvent(ListViewEvent.GetImages(3))
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