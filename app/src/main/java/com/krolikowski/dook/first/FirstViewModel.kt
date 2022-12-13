package com.krolikowski.dook.first

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
class FirstViewModel @Inject constructor(
    private val nasaRepository: NasaRepository
) : BaseViewModel<FirstViewEvent, FirstViewState>() {

    private var getImagesJob : Job? = null

    override fun onViewEvent(viewEvent: FirstViewEvent) {
        when (viewEvent) {
            is FirstViewEvent.GetImages -> getDebounced(viewEvent.imagesCount)
            is FirstViewEvent.CancelRequest -> cancelGetRequest()
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
        mutableViewState.postValue(FirstViewState.Success(images))

    private fun postLoadingState() = mutableViewState.postValue(FirstViewState.Loading)

    private fun postErrorState(error: String) =
        mutableViewState.postValue(FirstViewState.Error(error))

    companion object {
        private const val DEBOUNCE_TIME = 600L
    }
}