/*
 * Copyright 2022 Paul Rybitskyi, paul.rybitskyi.work@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.paulrybitskyi.gamedge.feature.info.widgets.header

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.paulrybitskyi.gamedge.commons.ui.theme.GamedgeTheme

@Composable
internal fun constructExpandedConstraintSet(
    hasDefaultPlaceholderArtwork: Boolean = false,
    isSecondTitleVisible: Boolean = false,
): ConstraintSet {
    val artworksHeight = 240.dp
    val pageIndicatorMargin = GamedgeTheme.spaces.spacing_2_5
    val coverSpaceMargin = COVER_SPACE
    val coverMarginStart = GamedgeTheme.spaces.spacing_3_5
    val likeBtnMarginEnd = GamedgeTheme.spaces.spacing_2_5
    val titleMarginStart = GamedgeTheme.spaces.spacing_3_5
    val firstTitleMarginTop = titleMarginStart
    val firstTitleMarginEnd = GamedgeTheme.spaces.spacing_1_0
    val secondTitleMarginEnd = GamedgeTheme.spaces.spacing_3_5
    val releaseDateMarginTop = GamedgeTheme.spaces.spacing_2_5
    val releaseDateMarginHorizontal = GamedgeTheme.spaces.spacing_3_5
    val developerNameMarginHorizontal = GamedgeTheme.spaces.spacing_3_5
    val bottomBarrierMargin = GamedgeTheme.spaces.spacing_5_0
    val infoItemMarginBottom = GamedgeTheme.spaces.spacing_3_5

    return ConstraintSet {
        val artworks = createRefFor(CONSTRAINT_ID_ARTWORKS)
        val artworksScrim = createRefFor(CONSTRAINT_ID_ARTWORKS_SCRIM)
        val backButton = createRefFor(CONSTRAINT_ID_BACK_BUTTON)
        val pageIndicator = createRefFor(CONSTRAINT_ID_PAGE_INDICATOR)
        val backdrop = createRefFor(CONSTRAINT_ID_BACKDROP)
        val coverSpace = createRefFor(CONSTRAINT_ID_COVER_SPACE)
        val cover = createRefFor(CONSTRAINT_ID_COVER)
        val likeButton = createRefFor(CONSTRAINT_ID_LIKE_BUTTON)
        val firstTitle = createRefFor(CONSTRAINT_ID_FIRST_TITLE)
        val secondTitle = createRefFor(CONSTRAINT_ID_SECOND_TITLE)
        val releaseDate = createRefFor(CONSTRAINT_ID_RELEASE_DATE)
        val developerName = createRefFor(CONSTRAINT_ID_DEVELOPER_NAME)
        val bottomBarrier = createBottomBarrier(cover, developerName, margin = bottomBarrierMargin)
        val rating = createRefFor(CONSTRAINT_ID_RATING)
        val likeCount = createRefFor(CONSTRAINT_ID_LIKE_COUNT)
        val ageRating = createRefFor(CONSTRAINT_ID_AGE_RATING)
        val gameCategory = createRefFor(CONSTRAINT_ID_GAME_CATEGORY)
        val list = createRefFor(CONSTRAINT_ID_LIST)

        constrain(artworks) {
            width = Dimension.fillToConstraints
            height = Dimension.value(artworksHeight)
            top.linkTo(parent.top)
            centerHorizontallyTo(parent)
        }
        constrain(artworksScrim) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            centerVerticallyTo(artworks)
            centerHorizontallyTo(artworks)
            visibility = if (hasDefaultPlaceholderArtwork) {
                Visibility.Gone
            } else {
                Visibility.Invisible
            }
        }
        constrain(backButton) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }
        constrain(pageIndicator) {
            top.linkTo(parent.top, pageIndicatorMargin)
            end.linkTo(parent.end, pageIndicatorMargin)
        }
        constrain(backdrop) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            top.linkTo(artworks.bottom)
            bottom.linkTo(list.top)
            centerHorizontallyTo(parent)
            translationZ = BACKDROP_ELEVATION_EXPANDED
        }
        constrain(coverSpace) {
            start.linkTo(parent.start)
            bottom.linkTo(artworks.bottom, coverSpaceMargin)
        }
        constrain(cover) {
            top.linkTo(coverSpace.bottom)
            start.linkTo(parent.start, coverMarginStart)
        }
        constrain(likeButton) {
            top.linkTo(artworks.bottom)
            bottom.linkTo(artworks.bottom)
            end.linkTo(parent.end, likeBtnMarginEnd)
        }
        constrain(firstTitle) {
            width = Dimension.fillToConstraints
            top.linkTo(artworks.bottom, firstTitleMarginTop)
            start.linkTo(cover.end, titleMarginStart)
            end.linkTo(likeButton.start, firstTitleMarginEnd)
        }
        constrain(secondTitle) {
            width = Dimension.fillToConstraints
            top.linkTo(firstTitle.bottom)
            start.linkTo(cover.end, titleMarginStart)
            end.linkTo(parent.end, secondTitleMarginEnd)
            visibility = if (isSecondTitleVisible) Visibility.Visible else Visibility.Gone
        }
        constrain(releaseDate) {
            width = Dimension.fillToConstraints
            top.linkTo(secondTitle.bottom, releaseDateMarginTop, releaseDateMarginTop)
            start.linkTo(cover.end, releaseDateMarginHorizontal)
            end.linkTo(parent.end, releaseDateMarginHorizontal)
        }
        constrain(developerName) {
            width = Dimension.fillToConstraints
            top.linkTo(releaseDate.bottom)
            start.linkTo(cover.end, developerNameMarginHorizontal)
            end.linkTo(parent.end, developerNameMarginHorizontal)
        }
        constrain(rating) {
            width = Dimension.fillToConstraints
            top.linkTo(bottomBarrier)
            bottom.linkTo(list.top, infoItemMarginBottom)
            linkTo(start = parent.start, end = likeCount.start, bias = 0.25f)
        }
        constrain(likeCount) {
            width = Dimension.fillToConstraints
            top.linkTo(bottomBarrier)
            bottom.linkTo(list.top, infoItemMarginBottom)
            linkTo(start = rating.end, end = ageRating.start, bias = 0.25f)
        }
        constrain(ageRating) {
            width = Dimension.fillToConstraints
            top.linkTo(bottomBarrier)
            bottom.linkTo(list.top, infoItemMarginBottom)
            linkTo(start = likeCount.end, end = gameCategory.start, bias = 0.25f)
        }
        constrain(gameCategory) {
            width = Dimension.fillToConstraints
            top.linkTo(bottomBarrier)
            bottom.linkTo(list.top, infoItemMarginBottom)
            linkTo(start = ageRating.end, end = parent.end, bias = 0.25f)
        }
        constrain(list) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            top.linkTo(rating.bottom)
            bottom.linkTo(parent.bottom)
            centerHorizontallyTo(parent)
        }
    }
}

@SuppressLint("Range")
@Composable
internal fun constructConstraintSet(
    artworksHeight: Dp,
    pageIndicatorTranslationX: Dp,
    coverTranslationX: Dp,
    coverTranslationY: Dp,
    coverAlpha: Float,
    likeButtonAlpha: Float,
    likeButtonScale: Float,
    mainTextAlpha: Float,
    mainTextTranslationX: Dp,
): ConstraintSet {
    val pageIndicatorMargin = GamedgeTheme.spaces.spacing_2_5
    val coverSpaceMargin = COVER_SPACE
    val coverMarginStart = GamedgeTheme.spaces.spacing_3_5
    val likeBtnMarginEnd = GamedgeTheme.spaces.spacing_2_5
    val titleMarginStart = GamedgeTheme.spaces.spacing_3_5
    val firstTitleMarginTop = titleMarginStart
    val firstTitleMarginEnd = GamedgeTheme.spaces.spacing_1_0
    val secondTitleMarginEnd = GamedgeTheme.spaces.spacing_3_5
    val releaseDateMarginTop = GamedgeTheme.spaces.spacing_2_5
    val releaseDateMarginHorizontal = GamedgeTheme.spaces.spacing_3_5
    val developerNameMarginHorizontal = GamedgeTheme.spaces.spacing_3_5
    val bottomBarrierMargin = GamedgeTheme.spaces.spacing_5_0
    val infoItemMarginBottom = GamedgeTheme.spaces.spacing_3_5

    return ConstraintSet {
        val artworks = createRefFor(CONSTRAINT_ID_ARTWORKS)
        val artworksScrim = createRefFor(CONSTRAINT_ID_ARTWORKS_SCRIM)
        val backButton = createRefFor(CONSTRAINT_ID_BACK_BUTTON)
        val pageIndicator = createRefFor(CONSTRAINT_ID_PAGE_INDICATOR)
        val backdrop = createRefFor(CONSTRAINT_ID_BACKDROP)
        val coverSpace = createRefFor(CONSTRAINT_ID_COVER_SPACE)
        val cover = createRefFor(CONSTRAINT_ID_COVER)
        val likeButton = createRefFor(CONSTRAINT_ID_LIKE_BUTTON)
        val firstTitle = createRefFor(CONSTRAINT_ID_FIRST_TITLE)
        val secondTitle = createRefFor(CONSTRAINT_ID_SECOND_TITLE)
        val releaseDate = createRefFor(CONSTRAINT_ID_RELEASE_DATE)
        val developerName = createRefFor(CONSTRAINT_ID_DEVELOPER_NAME)
        val bottomBarrier = createBottomBarrier(cover, developerName, margin = bottomBarrierMargin)
        val rating = createRefFor(CONSTRAINT_ID_RATING)
        val likeCount = createRefFor(CONSTRAINT_ID_LIKE_COUNT)
        val ageRating = createRefFor(CONSTRAINT_ID_AGE_RATING)
        val gameCategory = createRefFor(CONSTRAINT_ID_GAME_CATEGORY)

        constrain(artworks) {
            width = Dimension.fillToConstraints
            height = Dimension.value(artworksHeight)
            top.linkTo(parent.top)
            centerHorizontallyTo(parent)
        }
        constrain(artworksScrim) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            centerVerticallyTo(artworks)
            centerHorizontallyTo(artworks)
        }
        constrain(backButton) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }
        constrain(pageIndicator) {
            top.linkTo(parent.top, pageIndicatorMargin)
            end.linkTo(parent.end, pageIndicatorMargin)
            translationX = pageIndicatorTranslationX
        }
        constrain(backdrop) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            top.linkTo(artworks.bottom)
            bottom.linkTo(parent.bottom)
            centerHorizontallyTo(parent)
        }
        constrain(coverSpace) {
            start.linkTo(parent.start)
            bottom.linkTo(artworks.bottom, coverSpaceMargin)
        }
        constrain(cover) {
            top.linkTo(coverSpace.bottom)
            start.linkTo(parent.start, coverMarginStart)
            translationX = coverTranslationX
            translationY = coverTranslationY
            alpha = coverAlpha
        }
        constrain(likeButton) {
            top.linkTo(artworks.bottom)
            bottom.linkTo(artworks.bottom)
            end.linkTo(parent.end, likeBtnMarginEnd)
            alpha = likeButtonAlpha
            scaleX = likeButtonScale
            scaleY = likeButtonScale
        }
        constrain(firstTitle) {
            width = Dimension.fillToConstraints
            top.linkTo(artworks.bottom, firstTitleMarginTop)
            start.linkTo(cover.end, titleMarginStart)
            end.linkTo(likeButton.start, firstTitleMarginEnd)
            alpha = mainTextAlpha
            translationX = mainTextTranslationX
        }
        constrain(secondTitle) {
            width = Dimension.fillToConstraints
            top.linkTo(firstTitle.bottom)
            start.linkTo(cover.end, titleMarginStart)
            end.linkTo(parent.end, secondTitleMarginEnd)
            alpha = mainTextAlpha
            translationX = mainTextTranslationX
        }
        constrain(releaseDate) {
            width = Dimension.fillToConstraints
            top.linkTo(secondTitle.bottom, releaseDateMarginTop)
            start.linkTo(cover.end, releaseDateMarginHorizontal)
            end.linkTo(parent.end, releaseDateMarginHorizontal)
            alpha = mainTextAlpha
            translationX = mainTextTranslationX
        }
        constrain(developerName) {
            width = Dimension.fillToConstraints
            top.linkTo(releaseDate.bottom)
            start.linkTo(cover.end, developerNameMarginHorizontal)
            end.linkTo(parent.end, developerNameMarginHorizontal)
            alpha = mainTextAlpha
            translationX = mainTextTranslationX
        }
        constrain(rating) {
            width = Dimension.fillToConstraints
            top.linkTo(bottomBarrier)
            bottom.linkTo(parent.bottom, infoItemMarginBottom)
            linkTo(start = parent.start, end = likeCount.start, bias = 0.25f)
        }
        constrain(likeCount) {
            width = Dimension.fillToConstraints
            top.linkTo(bottomBarrier)
            bottom.linkTo(parent.bottom, infoItemMarginBottom)
            linkTo(start = rating.end, end = ageRating.start, bias = 0.25f)
        }
        constrain(ageRating) {
            width = Dimension.fillToConstraints
            top.linkTo(bottomBarrier)
            bottom.linkTo(parent.bottom, infoItemMarginBottom)
            linkTo(start = likeCount.end, end = gameCategory.start, bias = 0.25f)
        }
        constrain(gameCategory) {
            width = Dimension.fillToConstraints
            top.linkTo(bottomBarrier)
            bottom.linkTo(parent.bottom, infoItemMarginBottom)
            linkTo(start = ageRating.end, end = parent.end, bias = 0.25f)
        }
    }
}
