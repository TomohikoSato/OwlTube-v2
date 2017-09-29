package com.sgr.owltube_v2.common.ext

import android.content.Context
import com.facebook.stetho.Stetho

/**
 * debugビルドでのみStethoを含めるためのWrapperクラス
 */
object StethoWrapper {
    fun init(context: Context) = Stetho.initializeWithDefaults(context)
}