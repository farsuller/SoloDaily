package com.solo.solodaily.presentation.newsnavigator.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.solo.solodaily.presentation.common.SoloDailyPreviews
import com.solo.solodaily.ui.theme.SoloDailyTheme
import com.solo.solodaily.utils.TestTags.BOTTOM_NAVIGATION
import com.solo.solodaily.utils.TestTags.BOTTOM_NAVIGATION_ITEM_TEXT

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit,
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(BOTTOM_NAVIGATION),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = item.icon,
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            modifier = Modifier.testTag(BOTTOM_NAVIGATION_ITEM_TEXT),
                            text = item.text,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                    selectedTextColor = MaterialTheme.colorScheme.secondary,
                    unselectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    }
}

data class BottomNavigationItem(
    val icon: ImageVector,
    val text: String,
)

@SoloDailyPreviews
@Composable
internal fun NewsBottomNavigationPreview() {
    SoloDailyTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            NewsBottomNavigation(
                items = listOf(
                    BottomNavigationItem(icon = Icons.Filled.Home, text = "Home"),
                    BottomNavigationItem(icon = Icons.Filled.Search, text = "Search"),
                    BottomNavigationItem(icon = Icons.Filled.Bookmark, text = "Bookmark"),
                ),
                selected = 0,
                onItemClick = {},
            )
        }
    }
}
