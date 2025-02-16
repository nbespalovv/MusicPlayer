package com.nbespalovv.playeravito.model.dto

import com.google.gson.annotations.SerializedName

data class AlbumDto(
    @SerializedName("cover_medium")
    val image: String,
)
