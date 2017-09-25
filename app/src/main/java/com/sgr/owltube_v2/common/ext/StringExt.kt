package com.sgr.owltube_v2.common.ext

import java.text.Normalizer

fun String.normalize(target: String): String {
    if ((isHiragana(target) or isKatakana(target))) {
        // hankaku to zernkaku
        val zenkakukana = Normalizer.normalize(target, Normalizer.Form.NFKC)

        val sb = StringBuilder(zenkakukana.length)
        for (z in zenkakukana) {
            val c = (z.plus('あ'.toInt()).minus('ア'))
            sb.append(c)
        }
        return sb.toString()
    }

    return target
}

private fun isHiragana(target: String): Boolean {
    return (target.matches("^[\\u3040-\\u309F]+$".toRegex()))
}

private fun isKatakana(target: String): Boolean {
    return (target.matches("^[\\u30A0-\\u30FF]+$".toRegex()))
}

