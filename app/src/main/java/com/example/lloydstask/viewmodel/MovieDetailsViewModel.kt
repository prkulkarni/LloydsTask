package com.example.lloydstask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lloydstask.data.model.MovieDetailsModel
import com.example.lloydstask.di.IoDispatcher
import com.example.lloydstask.domain.usecases.MovieDetailsUseCase
import com.example.lloydstask.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: MovieDetailsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _movieDetailsState =
        MutableStateFlow<Result<MovieDetailsModel?>>(Result.Loading())
    val movieDetailsState: StateFlow<Result<MovieDetailsModel?>> get() = _movieDetailsState

    fun fetchMovieDetails(id: String) {
        viewModelScope.launch(ioDispatcher) {
            movieDetailsUseCase(id).collect {
                _movieDetailsState.value = it
            }
        }
    }
}