package com.krolikowski.dook.list

import androidx.lifecycle.viewModelScope
import com.krolikowski.dook.base.BaseViewModel
import com.krolikowski.dook.networking.NasaRepository
import com.krolikowski.dook.networking.entities.ImageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val nasaRepository: NasaRepository
) : BaseViewModel<ListViewEvent, ListViewState>() {

    private var getImagesJob : Job? = null

    override fun onViewEvent(viewEvent: ListViewEvent) {
        when (viewEvent) {
            is ListViewEvent.GetImages -> getDebounced(viewEvent.imagesCount)
            is ListViewEvent.CancelRequest -> cancelGetRequest()
        }
    }

    private fun getDebounced(imagesCount: Int) {
        getImagesJob?.cancel()
        getImagesJob = viewModelScope.launch {
                delay(DEBOUNCE_TIME)
                getImagesFromApi(imagesCount)
        }
    }

    private fun getImagesFromApi(imagesCount: Int) {
        postLoadingState()
        getImagesJob = viewModelScope.launch(Dispatchers.Default) {
            nasaRepository.getImagesFromNasa(imagesCount)
                .onSuccess {
                    postSuccessState(it)
                }
                .onFailure {
                    postErrorState(it.message ?: "")
                }
        }
    }

    private fun cancelGetRequest() {
        getImagesJob?.cancel()
        postErrorState("User canceled job")
    }

    private fun postSuccessState(images: List<ImageEntity>) =
        mutableViewState.postValue(ListViewState.Success(images))

    private fun postLoadingState() = mutableViewState.postValue(ListViewState.Loading)

    private fun postErrorState(error: String) =
        mutableViewState.postValue(ListViewState.Error(error))

    companion object {
        private const val DEBOUNCE_TIME = 600L
    }
}