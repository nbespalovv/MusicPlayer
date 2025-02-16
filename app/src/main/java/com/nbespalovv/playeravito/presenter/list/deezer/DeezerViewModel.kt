package com.nbespalovv.playeravito.presenter.list.deezer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbespalovv.playeravito.domain.loadChartsUseCase.LoadChartsUseCase
import com.nbespalovv.playeravito.domain.playlistFlowUseCase.PlaylistFlowUseCase
import com.nbespalovv.playeravito.domain.searchRemotePlaylistUseCase.SearchRemotePlaylistUseCase
import com.nbespalovv.playeravito.model.common.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeezerViewModel @Inject constructor(
    private val loadChartsUseCase: LoadChartsUseCase,
    private val playlistFlowUseCase: PlaylistFlowUseCase,
    private val searchRemotePlaylistUseCase: SearchRemotePlaylistUseCase,
) : ViewModel() {
    private val _playlist = MutableLiveData<List<Song>>()
    val playlist: LiveData<List<Song>>
        get() = _playlist

    init {
        viewModelScope.launch(Dispatchers.Default) {
            playlistFlowUseCase().collect { songs ->
                _playlist.postValue(songs)
            }
        }
        loadChart()
    }

    fun loadChart() {
        viewModelScope.launch {
            loadChartsUseCase()
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            if (query.isNotEmpty())
                searchRemotePlaylistUseCase(query)
            else loadChart()
        }
    }
}