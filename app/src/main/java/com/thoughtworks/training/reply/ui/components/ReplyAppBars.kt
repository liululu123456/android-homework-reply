package com.thoughtworks.training.reply.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thoughtworks.training.reply.R
import com.thoughtworks.training.reply.data.Email
import com.thoughtworks.training.reply.ui.ReplyEmailDetail
import com.thoughtworks.training.reply.ui.ReplyEmailList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplyDockedSearchBar(
    emails: List<Email>,
    selectedIds: Set<Long>,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }
    val searchResults = remember { mutableStateListOf<Email>() }

    LaunchedEffect(query) {
        searchResults.clear()

        if (query.isNotEmpty()) {
            val filteredEmails = emails.filter { email ->
                email.createdAt.contains(query, ignoreCase = true) ||
                        email.sender.firstName.contains(query, ignoreCase = true) ||
                        email.subject.contains(query, ignoreCase = true)||
                        email.body.contains(query, ignoreCase = true)
            }
            searchResults.addAll(filteredEmails)
        }
    }
    Column {
        EmailSearchBar(
            query = query,
            onValueChange = {query = it})
        val finalEmailList = if (query.isNotEmpty()) searchResults else emails
        ReplyEmailList(
            emails = finalEmailList,
            selectedEmailIds = selectedIds)
    }

}

@Composable
fun EmailSearchBar(
    query: String,
    onValueChange: (String) -> Unit,
) {
    Row {
        TextField(
            value = query,
            onValueChange = onValueChange,
            placeholder = {
                Row(Modifier.padding(horizontal = 4.dp)) {
                    Image(imageVector = Icons.Default.Search,
                        contentDescription = "SEARCH",
                        modifier = Modifier
                            .weight(1F)
                            .align(Alignment.CenterVertically),
                        )
                    Text("Search emails",
                        modifier = Modifier.weight(10F)
                            .align(Alignment.CenterVertically),)
                    AsyncImage(
                        model = R.drawable.avatar_0,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(30.dp)
                            .weight(1F),
                        contentScale = ContentScale.Crop,
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(36.dp)),
        )

    }

}
