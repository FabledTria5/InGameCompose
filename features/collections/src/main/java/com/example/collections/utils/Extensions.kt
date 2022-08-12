package com.example.collections.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun LocalDate.toCalendarFormat() =
    format(DateTimeFormatter.ofPattern("dd MMM. yyyy", Locale.ENGLISH))