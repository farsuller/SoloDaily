package com.solo.solodaily.presentation.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

fun Modifier.shimmerEffect() = this.then(
    composed {
        val transition = rememberInfiniteTransition(label = "")
        val alpha = transition.animateFloat(
            initialValue = 0.2f,
            targetValue = 0.9f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "",
        ).value
        background(color = MaterialTheme.colorScheme.tertiary.copy(alpha = alpha))
    },
)

@Composable
fun ArticleCardShimmerEffect(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect(),
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(start = 16.dp, top = 10.dp, end = 10.dp)
                .height(96.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .shimmerEffect(),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .shimmerEffect(),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

            ) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(15.dp)
                        .shimmerEffect(),
                )
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(100.dp)
                        .height(15.dp)
                        .shimmerEffect(),
                )

            }
        }
    }
}

@SoloDailyPreviews
@Composable
internal fun ShimmerEffectPreview() {
    ArticleCardShimmerEffect()
}
