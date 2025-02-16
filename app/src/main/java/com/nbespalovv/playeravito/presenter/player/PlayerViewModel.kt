package com.nbespalovv.playeravito.presenter.player

import android.content.ComponentName
import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.nbespalovv.playeravito.model.common.Song
import com.nbespalovv.playeravito.services.MediaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
) : ViewModel() {

    private val _isPlaying = MutableLiveData<Boolean>()
    val isPlaying: LiveData<Boolean>
        get() = _isPlaying
    var player: Player? = null
        private set

    fun initPlayer(
        context: Context,
        onPlayerReady: () -> Unit,
    ) {
        if (player != null) return

        val sessionToken = SessionToken(context, ComponentName(context, MediaService::class.java))
        val controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()

        viewModelScope.launch(Dispatchers.IO) {
            player = controllerFuture.get()
            withContext(Dispatchers.Main) {
                onPlayerReady()
                player?.addListener(
                    object : Player.Listener {
                        override fun onIsPlayingChanged(isPlaying: Boolean) {
                            _isPlaying.postValue(isPlaying)
                        }
                    }
                )
            }
        }
    }

    fun addSong(song: Song) {
        MediaItem.Builder()
            .setUri(
                song.preview
            )
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setArtist(song.artist)
                    .setTitle(song.title)
                    .setArtworkUri(Uri.parse(song.album))
                    .build()
            )
            .build().also { media ->
                player?.addMediaItem(media)
            }

    }

    fun playSong(position: Int) {
        player?.seekTo(position, 0)
        player?.prepare()
        player?.play()
    }

    fun playLatestSong() {
        val lastMediaItemIndex = player?.mediaItemCount?.let { it - 1 }
        lastMediaItemIndex ?: return
        playSong(lastMediaItemIndex)
    }

    override fun onCleared() {
        player = null
        super.onCleared()
    }
}