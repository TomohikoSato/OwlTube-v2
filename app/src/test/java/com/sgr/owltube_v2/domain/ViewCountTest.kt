package com.sgr.owltube_v2.domain

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class ViewCountTest {

    @get:Rule
    var thrown = ExpectedException.none()

    @Test
    @Parameters(method = "provideViewCount")
    fun show_valid(expected: String, viewCountValue: Int) {
        assertEquals(expected, ViewCount(viewCountValue).show())
    }

    fun provideViewCount(): Array<Array<out Any>> =
            arrayOf(
                    arrayOf("21億回", Int.MAX_VALUE), //カンナムスタイル31億回
                    arrayOf("1億回", 100000000),
                    arrayOf("9,999万回", 99999999),
                    arrayOf("4,952万回", 49521346),
                    arrayOf("36万回", 362940),
                    arrayOf("1万回", 10000),
                    arrayOf("9,999回", 9999),
                    arrayOf("583回", 583),
                    arrayOf("100回", 100),
                    arrayOf("28回", 28),
                    arrayOf("1回", 1),
                    arrayOf("0回", 0)
            )

    @Test
    @Parameters(method = "provideInvalidViewCount")
    fun show_invalid(expectedException: Throwable, viewCountValue: Int) {
        thrown.expect(expectedException::class.java)
        ViewCount(viewCountValue).show()
    }

    fun provideInvalidViewCount(): Array<Array<out Any>> =
            arrayOf(
                    arrayOf(IllegalStateException(), -1),
                    arrayOf(IllegalStateException(), Int.MIN_VALUE)
            )
}



