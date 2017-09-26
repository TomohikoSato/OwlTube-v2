package com.sgr.owltube_v2.common.ext

import android.os.Build
import android.util.Log
import java.util.regex.Pattern

object Logger {
    fun d(message: String) {
        Log.d(TagGenerater.getTag(), message)
    }

    fun w(message: String) {
        Log.d(TagGenerater.getTag(), message)
    }

    fun w(t: Throwable) {
        Log.d(TagGenerater.getTag(), t.message)
    }

    fun e(message: String) {
        Log.d(TagGenerater.getTag(), message)
    }

    fun e(t: Throwable) {
        Log.d(TagGenerater.getTag(), t.message)
    }

    private object TagGenerater {
        private const val CALL_STACK_INDEX = 2
        private const val MAX_TAG_LENGTH = 23
        private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")

        fun getTag(): String {
            // DO NOT switch this to Thread.getCurrentThread().getStackTrace(). The test will pass
            // because Robolectric runs them on the JVM but on Android the elements are different.
            val stackTrace = Throwable().stackTrace
            if (stackTrace.size <= CALL_STACK_INDEX) {
                throw IllegalStateException(
                        "Synthetic stacktrace didn't have enough elements: are you using proguard?")
            }
            return createStackElementTag(stackTrace[CALL_STACK_INDEX])
        }

        /**
         * Extract the tag which should be used for the message from the `element`. By default
         * this will use the class name without any anonymous class suffixes (e.g., `Foo$1`
         * becomes `Foo`).
         *
         * Note: This will not be called if a [manual tag][.tag] was specified.
         */
        private fun createStackElementTag(element: StackTraceElement): String {
            var tag = element.className
            val m = ANONYMOUS_CLASS.matcher(tag)
            if (m.find()) {
                tag = m.replaceAll("")
            }
            tag = tag.substring(tag.lastIndexOf('.') + 1)
            // Tag length limit was removed in API 24.
            return if (tag.length <= MAX_TAG_LENGTH || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tag
            } else tag.substring(0, MAX_TAG_LENGTH)
        }
    }
}