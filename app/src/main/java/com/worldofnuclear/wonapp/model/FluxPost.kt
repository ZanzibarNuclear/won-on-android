package com.worldofnuclear.wonapp.model

import java.sql.Timestamp

data class FluxPost(
    val id: Int = 0,
    val author: FluxAuthor?,
    val content: String,
    val parentFluxPostId: Int?,
    val replyCount: Int,
    val boostCount: Int,
    val createdAt: Timestamp
)
