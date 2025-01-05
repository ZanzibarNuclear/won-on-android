package com.worldofnuclear.wonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.worldofnuclear.wonapp.ui.theme.WoNAppTheme

data class Post(
    val id: Long,
    val content: String,
    val author: String,
    val timestamp: Long
)

class MainActivity : ComponentActivity() {
    private val posts = mutableStateListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WoNAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        posts = posts,
                        onPostSubmit = { content ->
                            val newPost = Post(
                                id = System.currentTimeMillis(),
                                content = content,
                                author = "User123",
                                timestamp = System.currentTimeMillis()
                            )
                            posts.add(newPost)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, posts: List<Post>, onPostSubmit: (String) -> Unit) {
    var postContent by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        BasicTextField(
            value = postContent,
            onValueChange = { postContent = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(8.dp)
                .border(1.dp, Color.Gray),
            decorationBox = { innerTextField ->
                if (postContent.text.isEmpty()) {
                    Text("Share your idea...", color = Color.Gray)
                }
                innerTextField()
            }
        )
        Button(
            onClick = {
                if (postContent.text.isNotEmpty()) {
                    onPostSubmit(postContent.text)
                    postContent = TextFieldValue("") // Clear input
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Submit")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(posts) { post ->
                PostCard(post)
            }
        }
    }
}

@Composable
fun PostCard(post: Post) {
    val timeElapsed = formatTimeElapsed(post.timestamp)
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = post.author, style = MaterialTheme.typography.titleMedium)
            Text(text = timeElapsed, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.content, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(onClick = { /* Handle View */ }) { Text("View") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { /* Handle Boost */ }) { Text("Boost") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { /* Handle React */ }) { Text("React") }
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

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    WoNAppTheme {
        HomeScreen(posts = listOf()) {}
    }
}