package com.fabledt5.authentication.utils

import android.util.Patterns

fun String.isCorrectEmail() = !isEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()