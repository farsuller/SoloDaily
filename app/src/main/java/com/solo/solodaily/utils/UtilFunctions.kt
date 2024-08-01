package com.solo.solodaily.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun Context.sharedPreferences(name: String) = SharedPreferenceDelegate(this, name)

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateTimeVersionCodeO(inputDateTime: String): String {
    val dateTime = LocalDateTime.parse(inputDateTime, DateTimeFormatter.ISO_DATE_TIME)
    val month = dateTime.month.toString().substring(0, 3)
    val day = dateTime.dayOfMonth.toString().padStart(2, '0')
    val year = dateTime.year
    val hour = dateTime.hour
    return "$month. $day, $year, $hour hours"
}

fun formatDateTime(inputDateTime: String): String {
    val dateFormat = SimpleDateFormat("MMM. dd, yyyy, HH 'hours'", Locale.TAIWAN)
    val date = Date(inputDateTime)
    return dateFormat.format(date)
}
