package com.sgr.owltube_v2.domain

import junit.framework.Assert.assertEquals
import org.junit.Test

class ViewCountTest {
    @Test
    fun show() {
        assertEquals("1万回", ViewCount(10000).show())
        assertEquals("9900回", ViewCount(9999).show())
        assertEquals("100回", ViewCount(103).show())
        assertEquals("100回", ViewCount(1).show())
    }
}