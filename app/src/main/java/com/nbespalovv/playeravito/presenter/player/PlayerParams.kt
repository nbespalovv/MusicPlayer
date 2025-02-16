package com.nbespalovv.playeravito.presenter.player

import java.io.Serializable

data class PlayerParams(
    val id: Long,
    val title: String,
    val preview: String,
    val artist: String,
    val album: String,
): Serializable
