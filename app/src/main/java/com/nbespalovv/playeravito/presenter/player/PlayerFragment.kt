package com.nbespalovv.playeravito.presenter.player

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.nbespalovv.playeravito.R
import com.nbespalovv.playeravito.databinding.PlayerFragmentBinding
import com.nbespalovv.playeravito.di.ViewModelFactory
import com.nbespalovv.playeravito.di.appComponent
import com.nbespalovv.playeravito.model.common.Song
import javax.inject.Inject

class PlayerFragment : Fragment(R.layout.player_fragment) {
    private val args: PlayerFragmentArgs by navArgs()
    private val playerParams: PlayerParams by lazy {
        args.playerParams
    }

    private val binding: PlayerFragmentBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: PlayerViewModel by viewModels { viewModelFactory }

    override fun onStart() {
        initialize()
        super.onStart()
    }

    private fun initialize() {
        viewModel.initPlayer(requireContext()) {
            viewModel.addSong(playerParams.toSong())
            viewModel.playLatestSong()

            viewModel.isPlaying.observe(viewLifecycleOwner) { isPlaying ->
                binding.playPause.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        if (isPlaying)
                            android.R.drawable.ic_media_pause
                        else android.R.drawable.ic_media_play
                    )
                )

                binding.next.isEnabled = viewModel.player?.hasNextMediaItem() == true
                binding.previous.isEnabled = viewModel.player?.hasPreviousMediaItem() == true

                val coverUrl = viewModel.player?.currentMediaItem?.mediaMetadata?.artworkUri

                binding.albumCover.load(
                    coverUrl
                ) {
                    error(com.nbespalovv.shared.R.drawable.ic_stub)
                }

                binding.playerSongName.text = viewModel.player?.mediaMetadata?.title

                binding.playerSongArtist.text = viewModel.player?.mediaMetadata?.artist
            }
        }

        binding.playPause.setOnClickListener {
            if (viewModel.player?.isPlaying == true) {
                viewModel.player?.pause()
            }
            else {
                viewModel.player?.prepare()
                viewModel.player?.play()
            }
        }

        binding.previous.setOnClickListener {
            viewModel.player?.seekToPrevious()
        }

        binding.next.setOnClickListener {
            viewModel.player?.seekToNext()
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun PlayerParams.toSong(): Song =
        Song(
            id = id,
            title = title,
            preview = preview,
            artist = artist,
            album = album
        )
}