package com.sgr.owltube_v2.domain

import java.text.NumberFormat

/** 視聴回数 */
data class ViewCount(private val value: Int) {
    /**
     * 億単位、万単位がある
     * 3桁目にカンマがある
     */
    fun show(): String = when (value) {
        in 10000 * 10000..Int.MAX_VALUE -> (value / (10000 * 10000)).putComma() + "億回"
        in 10000 until 10000 * 10000 -> (value / 10000).putComma() + "万回"
        in 0 until 10000 -> value.putComma() + "回"
        in Int.MIN_VALUE until 0 -> error("invalid value: ${value}")
        else -> error("unreachable")
    }

    /**
     * ３桁目にカンマを追加
     * e.g. 1000 -> 1,000
     */
    private fun Int.putComma(): String = NumberFormat.getInstance().format(this)
}



