package com.sgr.owltube_v2.domain

import org.threeten.bp.*


data class Video(val id: String,
                 val title: String,
                 val channel: Channel,
                 val viewCount: String,
                 val thumbnailUrl: String,
                 val _publishedAt: String,
                 val _duration: String) {

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

    val duration: String
        get() {
            val d = Duration.parse(_duration)
            return if (d.compareTo(Duration.ofHours(1)) >= 0)
                String.format("%d:%02d:%02d", d.toHours(), d.toMinutes() % 60, d.seconds % 60) else
                String.format("%02d:%02d", d.toMinutes() % 60, d.seconds % 60)
        }
}