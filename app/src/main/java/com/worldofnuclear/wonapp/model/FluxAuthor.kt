package com.worldofnuclear.wonapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FluxAuthor(
    val id: Int,
    val handle: String,
    @SerialName(value = "display_name")
    val displayName: String,
)
