package com.sgr.owltube_v2.domain

import com.sgr.owltube_v2.frontend.player.PlayerAdapterItem
import org.threeten.bp.*
import java.io.Serializable


data class Video(val id: String,
                      val title: String,
                      val channel: Channel,
                      private val _viewCount: String,
                      val thumbnailUrl: String,
                      private val _publishedAt: String,
                      private val _duration: String) : Serializable, PlayerAdapterItem {

    val publishedAt: String
        get() {
            val period = Period.between(OffsetDateTime.parse(_publishedAt).toLocalDate(), LocalDate.now())

            if (period.years > 0) return String.format("%d年前", period.years)
            if (period.months > 0) return String.format("%dヶ月前", period.months)
            if (period.days >= 7) return String.format("%d週間前", period.days / 7)
            if (period.days > 1) return String.format("%d日前", period.days)

            val duration = Duration.between(OffsetDateTime.parse(_publishedAt).toLocalDateTime(), LocalDateTime.now())
            if (duration.toHours() > 0) return String.format("%d時間前", duration.toHours())
            if (duration.toMinutes() > 0) return String.format("%d分前", duration.toMinutes())

            return String.format("%d秒前", duration.seconds)
        }

    val viewCount: String
        get() {
            val l = _viewCount.toLong()
            return if (l > 9999) String.format("%d万回", l / 10000) else String.format("%d回", l % 100 * 100)
        }

    val duration: String
        get() {
            val d = Duration.parse(_duration)
            return if (d.compareTo(Duration.ofHours(1)) >= 0)
                String.format("%d:%02d:%02d", d.toHours(), d.toMinutes() % 60, d.seconds % 60) else
                String.format("%02d:%02d", d.toMinutes() % 60, d.seconds % 60)
        }
}