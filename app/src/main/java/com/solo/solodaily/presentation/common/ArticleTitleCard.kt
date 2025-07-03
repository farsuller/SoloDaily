package com.solo.solodaily.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.solo.solodaily.R
import com.solo.solodaily.domain.model.Article
import com.solo.solodaily.domain.model.Source
import com.solo.solodaily.ui.theme.SoloDailyTheme

@Composable
fun ArticleTitleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    ElevatedCard(
        modifier = modifier
            .size(width = 130.dp, height = 160.dp)
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .clickable { onClick() },
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(96.dp)
                    .clip(MaterialTheme.shapes.medium),
                model = ImageRequest
                    .Builder(context)
                    .data(article.urlToImage)
                    .placeholder(R.drawable.solodaily_logo)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )

            val title = article.title.let {
                if (it.length > 14) it.take(10) + ".." else it
            }

            Text(
                text = title,
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }


}

@SoloDailyPreviews
@Composable
internal fun ArticleTitleCardPreview() {
    SoloDailyTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            ArticleTitleCard(
                article = Article(
                    author = "Karissa Bell",
                    content = "The Securities and Exchange Commission has approved\r\n the applications of 11 spot bitcoin ETFs in a highly anticipated decision that will make it much easier for people to dabble in cryptocurrency in… [+1453 chars]",
                    title = "SEC approves bitcoin ETFs (for real this time) SEC approves bitcoin ETFs (for real this time)",
                    description = "The Securities and Exchange Commission has approved\r\n the applications of 11 spot bitcoin ETFs in a highly anticipated decision that will make it much easier for people to dabble in cryptocurrency investing without directly buying and holding bitcoin. The app…",
                    url = "https://www.engadget.com/sec-approves-bitcoin-etfs-for-real-this-time-224125584.html",
                    urlToImage = "https://s.yimg.com/ny/api/res/1.2/n6iLNJ_9dtK.fT6WAXK1sA--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD03OTU-/https://s.yimg.com/os/creatr-uploaded-images/2024-01/3edf5140-afdd-11ee-bf7c-7918e1b9d963",
                    publishedAt = "2024-01-10T22:41:25Z",
                    source = Source(id = "engadget", name = "Engadget"),
                ),
                onClick = {},
            )
        }
    }
}
