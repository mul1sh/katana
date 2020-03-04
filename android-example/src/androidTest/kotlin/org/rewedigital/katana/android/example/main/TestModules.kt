@file:Suppress("TestFunctionName")

package org.rewedigital.katana.android.example.main

import androidx.test.espresso.idling.CountingIdlingResource
import org.rewedigital.katana.Module
import org.rewedigital.katana.android.example.remote.*
import org.rewedigital.katana.dsl.get
import org.rewedigital.katana.dsl.singleton

val TestSuccessApiMockModule = Module("TestSuccessApiMockModule") {

    singleton<JsonPlaceholderApi> {
        JsonPlaceholderApiSuccessMock()
    }
}

val TestErrorApiMockModule = Module("TestErrorApiMockModule") {

    singleton<JsonPlaceholderApi> {
        JsonPlaceholderApiErrorMock()
    }
}

const val TEST_REPO = "TEST_REPO"
const val TEST_IDLING_RESOURCE = "TEST_IDLING_RESOURCE"

fun TestRepositoryModule(repositoryIdlingResource: CountingIdlingResource) =
    Module("testRepositoryModule") {

        singleton(name = TEST_IDLING_RESOURCE) { repositoryIdlingResource }

        singleton<JsonPlaceholderRepository>(name = TEST_REPO) { JsonPlaceholderRepositoryImpl(get()) }

        singleton<JsonPlaceholderRepository> {
            TestJsonPlaceholderRepository(
                get(TEST_REPO),
                get(TEST_IDLING_RESOURCE)
            )
        }
    }
