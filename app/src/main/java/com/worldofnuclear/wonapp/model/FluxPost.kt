package com.worldofnuclear.wonapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.Timestamp

@Serializable
data class FluxPost(
    val id: Int = 0,
    @SerialName(value = "flux_user_id")
    val fluxUserId: Int,
    val author: FluxAuthor?,
    val content: String,
    @SerialName(value = "parent_id")
    val parentFluxPostId: Int?,
    @SerialName(value = "reply_count")
    val replyCount: Int,
    @SerialName(value = "boost_count")
    val boostCount: Int,
    @SerialName(value = "view_count")
    val viewCount: Int,
    @SerialName(value = "created_at")
    val createdAt: Timestamp,
    @SerialName(value = "updated_at")
    val updatedAt: Timestamp,
)
