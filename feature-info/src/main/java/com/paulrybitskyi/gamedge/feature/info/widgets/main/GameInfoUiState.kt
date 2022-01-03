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

package com.paulrybitskyi.gamedge.feature.info.widgets.main

internal data class GameInfoUiState(
    val isLoading: Boolean,
    val game: GameInfoModel?,
)

internal val GameInfoUiState.isInLoadingState: Boolean
    get() = (isLoading && (game == null))

internal val GameInfoUiState.isInEmptyState: Boolean
    get() = (!isLoading && (game == null))

internal val GameInfoUiState.isInSuccessState: Boolean
    get() = (game != null)

internal fun GameInfoUiState.toLoadingState(): GameInfoUiState {
    return copy(isLoading = true)
}

internal fun GameInfoUiState.toEmptyState(): GameInfoUiState {
    return copy(isLoading = false, game = null)
}

internal fun GameInfoUiState.toSuccessState(game: GameInfoModel): GameInfoUiState {
    return copy(isLoading = false, game = game)
}
