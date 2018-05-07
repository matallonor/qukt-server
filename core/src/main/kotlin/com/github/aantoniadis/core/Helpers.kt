package com.github.aantoniadis.core

import java.util.*

fun <T> Optional<T>.unwrap(): T? = orElse(null)

fun <T, E> Optional<T>.unwrap(conv: (T) -> (E)): E? = unwrap()?.let { conv(it) }