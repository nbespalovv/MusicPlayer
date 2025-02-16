package com.nbespalovv.playeravito.dto

import com.google.gson.annotations.SerializedName

data class TrackDto(
    @SerializedName("data")
    val data: List<SongDto>,
)
