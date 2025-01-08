package com.worldofnuclear.wonapp.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.worldofnuclear.wonapp.R
import com.worldofnuclear.wonapp.model.FluxPost
import java.time.Instant

@Composable
fun FluxMainScreen(
    viewModel: FluxViewModel = viewModel(),
    modifier: Modifier = Modifier,
) {
    val fluxUiState = viewModel.fluxUiState
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        FluxEntryForm(modifier)
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        FluxScroller(fluxUiState, modifier)
    }
}

@Composable
fun FluxEntryForm(
    modifier: Modifier = Modifier
) {
    var postContent by remember { mutableStateOf(TextFieldValue()) }

    BasicTextField(
        value = postContent,
        onValueChange = { postContent = it },
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.flux_entry_box_height))
            .padding(dimensionResource(R.dimen.padding_small))
            .border(1.dp, Color.Gray),
        decorationBox = { innerTextField ->
            if (postContent.text.isEmpty()) {
                Text(stringResource(R.string.flux_prompt), color = Color.Gray)
            }
            innerTextField()
        }
    )
    Button(
        onClick = {
            if (postContent.text.isNotEmpty()) {
// TODO                   viewModel.submitPost(postContent.text)
                postContent = TextFieldValue("") // Clear input
            }
        },
        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
    ) {
        Text(stringResource(R.string.submit_flux))
    }
}

@Composable
fun FluxScroller(
    fluxUiState: FluxUiState,
    modifier: Modifier = Modifier
) {
    when (fluxUiState) {
        is FluxUiState.Loading ->
            Text(stringResource(R.string.loading_flux), modifier = modifier.fillMaxSize())

        is FluxUiState.Success -> {
            val envelope = fluxUiState.fluxes
            if (envelope.total == 0) {
                Text(stringResource(R.string.no_flux), modifier = modifier.fillMaxSize())
            } else {
                LazyColumn {
                    items(envelope.items) { post ->
                        PostCard(post)
                    }
                }
            }
        }

        else ->
            Text(stringResource(R.string.failed_flux_loading), modifier = modifier.fillMaxSize())
    }
}

@Composable
fun PostCard(post: FluxPost) {
    val createdAtTs = Instant.parse(post.createdAt).toEpochMilli()
    val timeElapsed = formatTimeElapsed(createdAtTs)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.v_spacing_single))
    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            Row {
                post.author?.let {
                    Text(
                        text = it.displayName,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 2.dp)
                    )
                    Text(
                        text = stringResource(R.string.handle, it.handle),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 2.dp)
                    )
                }
            }
            Text(
                text = timeElapsed,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.v_spacing_double)))
            Text(
                text = AnnotatedString.fromHtml(post.content),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.v_spacing_double)))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /* Handle View */ }) {
                    Icon(
                        Icons.Outlined.Face,
                        contentDescription = "views",
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    Text(
                        text = post.viewCount.toString(),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Button(onClick = { /* Handle Boost */ }) {
                    Icon(
                        painter = painterResource(R.drawable.boost),
                        contentDescription = "boosts",
                        modifier = Modifier
                            .padding(end = 2.dp)
                            .size(24.dp)
                    )
                    Text(
                        post.boostCount.toString(),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Button(onClick = { /* Handle React */ }) {
                    Icon(
                        Icons.Outlined.Edit,
                        contentDescription = "reactions",
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    Text(
                        post.replyCount.toString(),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

fun formatTimeElapsed(timestamp: Long): String {
    val elapsed = System.currentTimeMillis() - timestamp
    val seconds = (elapsed / 1000).toInt()
    return when {
        seconds < 60 -> "$seconds seconds ago"
        seconds < 3600 -> "${seconds / 60} minutes ago"
        seconds < 86400 -> "${seconds / 3600} hours ago"
        else -> "${seconds / 86400} days ago"
    }
}
