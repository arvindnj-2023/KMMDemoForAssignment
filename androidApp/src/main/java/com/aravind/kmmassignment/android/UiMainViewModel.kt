package com.aravind.kmmassignment.android

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.aravind.kmmassignment.model.Breed
import com.aravind.kmmassignment.repository.BreedsRepository
import com.aravind.kmmassignment.usecase.FetchBreedsUseCase
import com.aravind.kmmassignment.usecase.GetBreedsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UiMainViewModel(
    breedsRepository: BreedsRepository,
    private val getBreeds: GetBreedsUseCase,
    private val fetchBreeds: FetchBreedsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State.LOADING)
    val state: StateFlow<State> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private val _events = MutableSharedFlow<Event>()
    val events: SharedFlow<Event> = _events

    private val _shouldFilterBreedTypes = MutableStateFlow(false)
    val shouldFilterBreedTypes: StateFlow<Boolean> = _shouldFilterBreedTypes

    val breeds =
        breedsRepository.breeds.combine(shouldFilterBreedTypes) { breeds, shouldFilterBreedTypes ->
            if (shouldFilterBreedTypes) {
                breeds.filter { false }
            } else {
                breeds
            }.also {
                _state.value = if (it.isEmpty()) State.EMPTY else State.NORMAL
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    init {
        loadData()
    }

    private fun loadData(isForceRefresh: Boolean = false) {
        val getData: suspend () -> List<Breed> =
            { if (isForceRefresh) fetchBreeds() else getBreeds() }

        if (isForceRefresh) {
            _isRefreshing.value = true

        } else {
            _state.value = State.LOADING
        }

        viewModelScope.launch {
            _state.value = try {
                getData()
                State.NORMAL
            } catch (e: Exception) {
                State.ERROR
            }
            _isRefreshing.value = false
        }
    }

    fun refresh() {
        loadData(true)
    }
    enum class State {
        LOADING,
        NORMAL,
        ERROR,
        EMPTY
    }

    enum class Event {
        Error
    }
}