package com.nbespalovv.shared

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nbespalovv.shared.databinding.SongItemLayoutBinding

class SongItemAdapter(
    private val onSongClick: (Int, SongShared) -> Unit,
): ListAdapter<SongShared, SongItemAdapter.SongItemViewHolder>(SongItemDiffUtil()) {

    class SongItemViewHolder(
        private val binding: SongItemLayoutBinding,
        private val onSongClick: (Int, SongShared) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(songItem: SongShared, position: Int) {
            binding.songArtist.text = songItem.artist
            binding.songTitle.text = songItem.title
            binding.songCaver.load(songItem.album) {
                error(R.drawable.ic_stub)
            }

            binding.root.setOnClickListener {
                onSongClick(position, songItem)
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SongItemViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = SongItemLayoutBinding.inflate(inflater, parent, false)
        return SongItemViewHolder(binding, onSongClick)
    }

    override fun onBindViewHolder(holder: SongItemViewHolder, position: Int) {
        val songItem = getItem(position)
        holder.bind(songItem, position)
    }

    class SongItemDiffUtil: DiffUtil.ItemCallback<SongShared>() {
        override fun areItemsTheSame(oldItem: SongShared, newItem: SongShared): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SongShared, newItem: SongShared): Boolean =
            oldItem == newItem

    }

}