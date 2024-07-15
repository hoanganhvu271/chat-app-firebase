package com.hav.chat_app.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Trans {
    companion object {

        fun timestampToTime(timestamp: Long): String {
            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val dateTime = Date(timestamp)
            return dateFormat.format(dateTime).toString()
        }

    }
}