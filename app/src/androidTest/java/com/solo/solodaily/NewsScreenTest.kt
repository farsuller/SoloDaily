package com.solo.solodaily

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.solo.solodaily.presentation.common.SearchBar
import com.solo.solodaily.presentation.home.HeaderSoloDaily
import com.solo.solodaily.presentation.home.TitleMarquees
import com.solo.solodaily.presentation.newsnavigator.component.BottomNavigationItem
import com.solo.solodaily.presentation.newsnavigator.component.NewsBottomNavigation
import com.solo.solodaily.utils.TestTags.HEADER_SOLODAILY
import com.solo.solodaily.utils.TestTags.SEARCH_BAR
import com.solo.solodaily.utils.TestTags.TITLE_MARQUEES
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsScreenTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    private var items = listOf(
        BottomNavigationItem(icon = Icons.Filled.Home, text = "Home"),
        BottomNavigationItem(icon = Icons.Filled.Search, text = "Search"),
        BottomNavigationItem(icon = Icons.Filled.Bookmark, text = "Bookmark"),
    )

    @Test
    fun navBar_Assert_BottomNavBarItemsDisplayed() {
        composeTestRule.setContent {
            NewsBottomNavigation(
                items = items,
                selected = 0,
                onItemClick = {},
            )
        }

        // Verify if "Home" item is displayed
        composeTestRule.onNodeWithText("Home").assertExists()

        // Verify if "Search" item is displayed
        composeTestRule.onNodeWithText("Search").assertExists()

        // Verify if "Bookmark" item is displayed
        composeTestRule.onNodeWithText("Bookmark").assertExists()
    }


    @Test
    fun searchBar_Assert_SearchBarDisplayed() {
        composeTestRule.setContent {
            SearchBar(text = "", onValueChange = {}, readOnly = false, onSearch = {})
        }

        composeTestRule.onNodeWithTag(SEARCH_BAR).assertExists()
    }

    @Test
    fun header_Assert_HeaderDisplayed(){
        composeTestRule.setContent {
            HeaderSoloDaily()
        }

        composeTestRule.onNodeWithTag(HEADER_SOLODAILY).assertExists()
    }

    @Test
    fun titles_Assert_TitlesMarqueeDisplayed(){
        composeTestRule.setContent {
            TitleMarquees("Titles, Short Titles, Very Short Titles, Long Titles, Very Long Titles,Very Long Long Titles, ")
        }

        composeTestRule.onNodeWithTag(TITLE_MARQUEES).assertExists()
    }
}