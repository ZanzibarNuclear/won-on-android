package com.worldofnuclear.wonapp.model

import java.sql.Timestamp

data class FluxAuthor(
    val id: Int,
    val userId: String,
    val handle: String,
    val displayName: String,
    val createdAt: Timestamp,
    val updatedAt: Timestamp
)
