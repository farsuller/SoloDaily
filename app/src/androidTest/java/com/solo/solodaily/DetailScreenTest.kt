package com.solo.solodaily

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.solo.solodaily.domain.model.Article
import com.solo.solodaily.domain.model.Source
import com.solo.solodaily.presentation.details.DetailScreen
import com.solo.solodaily.presentation.details.components.DetailTopBar
import com.solo.solodaily.utils.TestTags.DETAIL_DESCRIPTION
import com.solo.solodaily.utils.TestTags.DETAIL_IMAGE
import com.solo.solodaily.utils.TestTags.DETAIL_TITLE
import com.solo.solodaily.utils.TestTags.DETAIL_TOP_BAR
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailScreenTest {

    @get: Rule
    val composeTestRule = createComposeRule()


    @Test
    fun detailTopBar_Assert_DetailTopBarDisplayed() {
        composeTestRule.setContent {
            DetailTopBar(
                onBrowsingClick = {},
                onShareClick = {},
                onBookmarkClick = {},
                onBackClick = {})
        }

        composeTestRule.onNodeWithTag(DETAIL_TOP_BAR).assertExists()

        composeTestRule.onNodeWithContentDescription("BackButton").assertExists()

        composeTestRule.onNodeWithContentDescription("BookmarkButton").assertExists()

        composeTestRule.onNodeWithContentDescription("ShareButton").assertExists()

        composeTestRule.onNodeWithContentDescription("BrowseButton").assertExists()
    }


    @Test
    fun detailScreen_Assert_DetailScreenDisplayed() {
        composeTestRule.setContent {
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

        composeTestRule.onNodeWithTag(DETAIL_IMAGE).assertExists()

        composeTestRule.onNodeWithTag(DETAIL_TITLE).assertExists()

        composeTestRule.onNodeWithTag(DETAIL_DESCRIPTION).assertExists()

    }

}