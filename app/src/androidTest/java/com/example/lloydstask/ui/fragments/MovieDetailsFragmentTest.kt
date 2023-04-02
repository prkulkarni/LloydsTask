package com.example.lloydstask.ui.fragments

import com.example.lloydstask.R
import com.example.lloydstask.ui.BaseActivityTest
import org.junit.Test

class MovieDetailsFragmentTest : BaseActivityTest() {

    @Test
    fun testMovieDetailsElements() {
        verifyViewWithIdIsDisplayed(R.id.rvMovieList)
        Thread.sleep(500)
        clickOnIdAtPosition(R.id.movieItemContainer, 0)
        verifyViewWithIdIsDisplayed(R.id.movieImage)
        verifyViewWithIdIsDisplayed(R.id.movieTitleText)
        verifyViewWithIdIsDisplayed(R.id.plotText)
    }
}