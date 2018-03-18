package com.sgr.owltube_v2.domain

import junit.framework.Assert
import org.junit.Test

class ViewCountTest {
    @Test
    fun show() {
        Assert.assertEquals("21億回", ViewCount(Int.MAX_VALUE).show())
        Assert.assertEquals("1億回", ViewCount(100000000).show())
        Assert.assertEquals("9,999万回", ViewCount(99999999).show())
        Assert.assertEquals("4,952万回", ViewCount(49521346).show())
        Assert.assertEquals("36万回", ViewCount(362940).show())
        Assert.assertEquals("1万回", ViewCount(10000).show())
        Assert.assertEquals("9999", ViewCount(9999).show())
        Assert.assertEquals("583回", ViewCount(583).show())
        Assert.assertEquals("100回", ViewCount(100).show())
        Assert.assertEquals("28回", ViewCount(28).show())
        Assert.assertEquals("1回", ViewCount(1).show())
        Assert.assertEquals("0回", ViewCount(1).show())
    }

/*    @Test
    @Parameters(method = "provideViewCount")
    fun show(expected: String, viewCountValue: Int) {
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


    @get:Rule
    var thrown = ExpectedException.none()

    @Test
    @Parameters(method = "provideInvalidViewCount")
    fun value_invalid(value: Int) {
        thrown.expect(IllegalArgumentException::class.java)
        ViewCount(value)
    }

    fun provideInvalidViewCount(): Array<Array<out Any>> =
            arrayOf(
                    arrayOf(-1),
                    arrayOf(Int.MIN_VALUE)
            )
            */
}



