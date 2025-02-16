package com.nbespalovv.shared

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nbespalovv.shared.databinding.FragmentSongsBinding

abstract class PlaylistFragment : Fragment(R.layout.fragment_songs) {

    private val binding: FragmentSongsBinding by viewBinding()
    private val adapter = SongItemAdapter(::onSongClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecycler()
        registerPlaylistCollector {
            this@PlaylistFragment.adapter.submitList(it)
        }
    }

    abstract fun onSongClick(index: Int, song: SongShared)

    abstract fun registerPlaylistCollector(collector: PlaylistCollector) : Unit
    private fun prepareRecycler() = with(binding.songsRecycler) {
        this.adapter = this@PlaylistFragment.adapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}