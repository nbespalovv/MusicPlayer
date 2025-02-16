package com.nbespalovv.playeravito.presenter.list.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbespalovv.playeravito.domain.localPlaylistFlowUseCase.LocalPlaylistFlowUseCase
import com.nbespalovv.playeravito.domain.localTracksUseCase.LocalTracksUseCase
import com.nbespalovv.playeravito.model.common.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocalPlaylistViewModel @Inject constructor(
    private val localTracksUseCase: LocalTracksUseCase,
    private val localPlaylistFlowUseCase: LocalPlaylistFlowUseCase
) : ViewModel() {
    private val _playlist = MutableLiveData<List<Song>>()
    val playlist: LiveData<List<Song>>
        get() = _playlist

    init {
        viewModelScope.launch(Dispatchers.Default) {
            localPlaylistFlowUseCase().collect { songs ->
                _playlist.postValue(songs)
            }
        }
        loadChart()
    }

    fun loadChart() {
        viewModelScope.launch {
            localTracksUseCase()
        }
    }
}