package com.nbespalovv.playeravito.dto

import com.google.gson.annotations.SerializedName
import com.nbespalovv.playeravito.model.dto.AlbumDto

data class SongDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("preview")
    val preview: String,
    @SerializedName("artist")
    val artist: ArtistDto,
    @SerializedName("album")
    val album: AlbumDto,
)



