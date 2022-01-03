/*
 * Copyright 2021 Paul Rybitskyi, paul.rybitskyi.work@gmail.com
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

package com.paulrybitskyi.gamedge.feature.category.widgets

internal data class GamesCategoryUiState(
    val isLoading: Boolean,
    val title: String,
    val games: List<GameCategoryModel>,
)

internal data class GameCategoryModel(
    val id: Int,
    val title: String,
    val coverUrl: String?
)

internal val GamesCategoryUiState.isInLoadingState: Boolean
    get() = (isLoading && games.isEmpty())

internal val GamesCategoryUiState.isInRefreshingState: Boolean
    get() = (isLoading && games.isNotEmpty())

internal val GamesCategoryUiState.isInEmptyState: Boolean
    get() = (!isLoading && games.isEmpty())

internal val GamesCategoryUiState.isInSuccessState: Boolean
    get() = games.isNotEmpty()

internal fun GamesCategoryUiState.toLoadingState(): GamesCategoryUiState {
    return copy(isLoading = true)
}

internal fun GamesCategoryUiState.toEmptyState(): GamesCategoryUiState {
    return copy(isLoading = false, games = emptyList())
}

internal fun GamesCategoryUiState.toSuccessState(
    games: List<GameCategoryModel>
): GamesCategoryUiState {
    return copy(isLoading = false, games = games)
}
