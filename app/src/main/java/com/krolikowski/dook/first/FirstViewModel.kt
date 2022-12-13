package com.krolikowski.dook.first

import androidx.lifecycle.viewModelScope
import com.krolikowski.dook.base.BaseViewModel
import com.krolikowski.dook.networking.NasaRepository
import com.krolikowski.dook.networking.entities.ImageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val nasaRepository: NasaRepository
) : BaseViewModel<FirstViewEvent, FirstViewState>() {

    init {
        getImagesFromApi(3)
    }

    override fun onViewEvent(viewEvent: FirstViewEvent) {
        when (viewEvent) {
            is FirstViewEvent.GetImages -> getImagesFromApi(viewEvent.imagesCount)
        }
    }

    private fun getImagesFromApi(imagesCount: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            postSuccessState(nasaRepository.getImagesFromNasa(imagesCount))
        }
    }

    private fun postSuccessState(images: List<ImageEntity>) =
        mutableViewState.postValue(FirstViewState.Success(images))

    private fun postLoadingState() = mutableViewState.postValue(FirstViewState.Loading)
}