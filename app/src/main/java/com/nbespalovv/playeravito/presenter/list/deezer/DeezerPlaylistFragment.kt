package com.nbespalovv.playeravito.presenter.list.deezer

import android.content.Context
import androidx.fragment.app.viewModels
import com.nbespalovv.playeravito.di.appComponent
import com.nbespalovv.playeravito.model.mappers.toSongShared
import com.nbespalovv.playeravito.di.ViewModelFactory
import com.nbespalovv.shared.PlaylistCollector
import com.nbespalovv.shared.PlaylistFragment
import com.nbespalovv.shared.SongShared
import javax.inject.Inject

class DeezerPlaylistFragment: PlaylistFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: DeezerViewModel by viewModels { viewModelFactory }
    override fun onSongClick(index: Int, song: SongShared) {

    }

    override fun registerPlaylistCollector(collector: PlaylistCollector) {
        viewModel.playlist.observe(viewLifecycleOwner) {
            collector.submitPlaylist(it.map { it.toSongShared() })
        }
    }

    override fun onSearch(query: String) {
        viewModel.search(query)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }
}