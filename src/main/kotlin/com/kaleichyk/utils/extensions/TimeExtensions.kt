package com.kaleichyk.utils.extensions

import java.util.*

fun getExpressionTime(tokenValidTime: Long) = Date(System.currentTimeMillis() + tokenValidTime)