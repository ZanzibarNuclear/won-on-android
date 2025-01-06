package com.worldofnuclear.wonapp.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.worldofnuclear.wonapp.R
import com.worldofnuclear.wonapp.model.FluxPost

@Composable
fun FluxMainScreen(
    modifier: Modifier = Modifier,
    viewModel: FluxViewModel = viewModel(),
) {
    var postContent by remember { mutableStateOf(TextFieldValue()) }
    val uiState by viewModel.uiState.collectAsState()
    val posts = uiState.fluxes



    Column(modifier = modifier
        .fillMaxSize()
        .padding(dimensionResource(R.dimen.padding_medium))) {
        BasicTextField(
            value = postContent,
            onValueChange = { postContent = it },
            modifier = Modifier
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
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        LazyColumn {
            items(posts) { post ->
                PostCard(post)
            }
        }
    }
}

@Composable
fun PostCard(post: FluxPost) {
    val timeElapsed = formatTimeElapsed(post.createdAt)
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = dimensionResource(R.dimen.v_spacing_single))) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            post.author?.let {
                Text(
                    text = it.handle,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(text = timeElapsed, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.v_spacing_double)))
            Text(text = post.content, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.v_spacing_double)))
            Row {
                Button(onClick = { /* Handle View */ }) { Text(stringResource(R.string.views)) }
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.v_spacing_double)))
                Button(onClick = { /* Handle Boost */ }) { Text(stringResource(R.string.boosts)) }
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.v_spacing_double)))
                Button(onClick = { /* Handle React */ }) { Text(stringResource(R.string.reactions)) }
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
        else -> "${seconds / 3600} hours ago"
    }
}
