package com.sgr.owltube_v2.domain

/** 視聴回数 */
data class ViewCount(private val value: Int) {
    fun show(): String = if (value > 9999) "${value / 10000}万回" else "${value % 100 * 100}回"
}