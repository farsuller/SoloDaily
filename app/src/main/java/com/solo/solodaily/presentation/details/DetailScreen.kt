package com.solo.solodaily.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.solo.solodaily.R
import com.solo.solodaily.domain.model.Article
import com.solo.solodaily.domain.model.Source
import com.solo.solodaily.presentation.common.SoloDailyPreviews
import com.solo.solodaily.presentation.details.components.DetailTopBar
import com.solo.solodaily.ui.theme.SoloDailyTheme

@Composable
fun DetailScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        DetailTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = { event(DetailsEvent.UpsertDeleteArticle(article)) },
            onBackClick = navigateUp,
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 24.dp,
                end = 24.dp,
                top = 24.dp,
            ),
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(248.dp)
                        .clip(MaterialTheme.shapes.medium),
                    model = ImageRequest
                        .Builder(context)
                        .placeholder(R.drawable.solodaily_logo)
                        .data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall.copy(fontSize = 30.sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 30.sp,
                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = article.description,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 25.sp,
                )
            }
        }
    }
}

@SoloDailyPreviews
@Composable
internal fun DetailScreenPreview() {
    SoloDailyTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            DetailScreen(
                article = Article(
                    author = "Karissa Bell",
                    content = "The Securities and Exchange Commission has approved\r\n the applications of 11 spot bitcoin ETFs in a highly anticipated decision that will make it much easier for people to dabble in cryptocurrency in… [+1453 chars]",
                    title = "SEC approves bitcoin ETFs (for real this time) ",
                    description = "The Securities and Exchange Commission has approved\r\n the applications of 11 spot bitcoin ETFs in a highly anticipated decision that will make it much easier for people to dabble in cryptocurrency investing without directly buying and holding bitcoin. The app…",
                    url = "https://www.engadget.com/sec-approves-bitcoin-etfs-for-real-this-time-224125584.html",
                    urlToImage = "https://s.yimg.com/ny/api/res/1.2/n6iLNJ_9dtK.fT6WAXK1sA--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD03OTU-/https://s.yimg.com/os/creatr-uploaded-images/2024-01/3edf5140-afdd-11ee-bf7c-7918e1b9d963",
                    publishedAt = "2024-01-10T22:41:25Z",
                    source = Source(id = "engadget", name = "Engadget"),
                ),
                event = {},
                navigateUp = {},
            )
        }
    }
}
