package com.sgr.owltube_v2.common.ext

import android.content.Context

/**
 * debugビルドでのみStethoを含めるためのWrapperクラス
 */
object StethoWrapper {
    fun init(context: Context) = { /*no op*/ }
}
