package com.example.lloydstask

import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before

@ExperimentalCoroutinesApi
open class BaseTest {

    val testDispatcher = StandardTestDispatcher()

    @Before
    open fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testDispatcher)
    }

    companion object {
        const val DEFAULT_ERROR_MESSAGE = "Something went wrong"
        const val MOVIE_ID = "12345678"
        const val MOVIE_LANGUAGE = "en"
    }
}