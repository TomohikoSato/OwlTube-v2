package com.sgr.owltube_v2.domain

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class ViewCountTest {
    @Test
    @Parameters(method = "provideViewCount")
    fun show(expected: String, viewCountValue: Int) {
        assertEquals(expected, ViewCount(viewCountValue).show())
    }

    fun provideViewCount(): Array<Array<out Any>> =
            arrayOf(
                    arrayOf("1万回", 10000),
                    arrayOf("9,999回", 9999),
                    arrayOf("103回", 103),
                    arrayOf("100回", 100),
                    arrayOf("1回", 1),
                    arrayOf("0回", 0)
            )
}