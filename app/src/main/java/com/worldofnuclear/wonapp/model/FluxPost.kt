package com.worldofnuclear.wonapp.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

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
    @Contextual
    val createdAt: String,
    @SerialName(value = "updated_at")
    @Contextual
    val updatedAt: String,
)

@Serializable
data class FluxPostResponse(
    val items: List<FluxPost>,
    val total: Int,
    val hasMore: Boolean
)

/*
data class Post(
    val id: Long,
    val content: String,
    val author: String,
    val timestamp: Long
)
 */