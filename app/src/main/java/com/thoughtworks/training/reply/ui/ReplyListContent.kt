package com.thoughtworks.training.reply.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.thoughtworks.training.reply.data.Email
import coil.compose.AsyncImage
import com.thoughtworks.training.reply.ui.theme.inverseOnSurfaceLight
import com.thoughtworks.training.reply.ui.theme.inversePrimaryLight
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.thoughtworks.training.reply.ui.components.ReplyDockedSearchBar

@Composable
fun ReplyInboxScreen(
    viewModel: ReplyHomeViewModel,
    modifier: Modifier = Modifier
) {
    val emailsStates by viewModel.emailsLiveData.observeAsState()
    val selectedIds = mutableSetOf<Long>()
    LaunchedEffect(Unit) {
        viewModel.fetchAllEmailData()
    }
    emailsStates?.let {
        ReplyDockedSearchBar(
        emails = it,
        selectedIds = selectedIds
            )
    }
}

@Composable
fun ReplyEmailList(
    emails: List<Email>,
    selectedEmailIds: Set<Long>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        items(emails.size) { i ->
            var backgroundColor by remember { mutableStateOf(inverseOnSurfaceLight) }
            var isSelected by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .padding(vertical = 4.dp)
            ){
                ReplyEmailDetail(
                    email = emails[i],
                    modifier = Modifier
                        .clickable {
                            selectedEmailIds.plus(emails[i].id)
                            isSelected = !isSelected
                            backgroundColor =
                                if (isSelected) inversePrimaryLight else inverseOnSurfaceLight
                        }
                        .background(backgroundColor),
                )
            }
            }
        }
}

@Composable
fun ReplyEmailDetail(
    email: Email,
    modifier: Modifier = Modifier,
) {
    var isLiked by remember { mutableStateOf(false) }
    var likedIcon by remember { mutableStateOf(Icons.Default.StarOutline) }


    Column (modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)
        .height(120.dp)
    )
    {
        Row(Modifier.padding(bottom = 4.dp)
        ) {
            AsyncImage(
                model = email.sender.avatar,
                contentDescription = null,
                modifier = Modifier
                    .weight(1.5f)
                    .clip(CircleShape)
                    .size(48.dp),
                contentScale = ContentScale.Crop,
            )
            Column(Modifier.padding(horizontal = 10.dp)
                .weight(8f)
            ) {
                Text(text = email.sender.firstName,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold)
                Text(text = email.createdAt,
                    fontSize =12.sp,
                    fontWeight = FontWeight.Bold)
            }
            Box(modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable{
                    isLiked = !isLiked
                    likedIcon = if (isLiked) Icons.Default.Star else Icons.Default.StarOutline
                },
            ){
                Image(
                    imageVector = likedIcon,
                    contentDescription = "LIKE",
                    modifier = Modifier.align(Alignment.Center)
                )
            }


        }
        Text(text = email.subject,
            fontSize = 20.sp
        )
        Text(text = email.body,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold)
    }
}