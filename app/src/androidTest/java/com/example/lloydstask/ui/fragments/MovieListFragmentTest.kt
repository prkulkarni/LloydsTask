package com.example.lloydstask.ui.fragments

import com.example.lloydstask.R
import com.example.lloydstask.ui.BaseActivityTest
import org.junit.Test

class MovieListFragmentTest : BaseActivityTest() {

    @Test
    fun testMovieListElements() {
        verifyViewWithIdIsDisplayed(R.id.rvMovieList)
        Thread.sleep(500)
        verifyViewWithIdIsDisplayedAtPosition(R.id.movieItemContainer, 0)
        verifyViewWithIdIsDisplayedAtPosition(R.id.thumbnailImage, 0)
        verifyViewWithIdIsDisplayedAtPosition(R.id.titleText, 0)
    }
}