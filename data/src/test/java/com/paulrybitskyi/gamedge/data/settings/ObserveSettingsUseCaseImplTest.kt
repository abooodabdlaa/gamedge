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

package com.paulrybitskyi.gamedge.data.settings

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.paulrybitskyi.gamedge.commons.testing.DATA_SETTINGS
import com.paulrybitskyi.gamedge.commons.testing.FakeDispatcherProvider
import com.paulrybitskyi.gamedge.data.settings.datastores.SettingsLocalDataStore
import com.paulrybitskyi.gamedge.data.settings.usecases.ObserveSettingsUseCaseImpl
import com.paulrybitskyi.gamedge.data.settings.usecases.commons.SettingsMapper
import com.paulrybitskyi.gamedge.domain.commons.extensions.execute
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ObserveSettingsUseCaseImplTest {

    @MockK private lateinit var settingsLocalDataStore: SettingsLocalDataStore

    private lateinit var settingsMapper: SettingsMapper
    private lateinit var SUT: ObserveSettingsUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        settingsMapper = SettingsMapper()
        SUT = ObserveSettingsUseCaseImpl(
            localDataStore = settingsLocalDataStore,
            dispatcherProvider = FakeDispatcherProvider(),
            settingsMapper = settingsMapper,
        )
    }

    @Test
    fun `Emits settings from local data store`() {
        runTest {
            every { settingsLocalDataStore.observeSettings() } returns flowOf(DATA_SETTINGS)

            SUT.execute().test {
                assertThat(awaitItem()).isEqualTo(settingsMapper.mapToDomainSettings(DATA_SETTINGS))
                awaitComplete()
            }
        }
    }
}
