package chapter04

import java.time.LocalTime
import java.time.format.DateTimeFormatter

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

fun getCurrentTime(): String = LocalTime.now().format(formatter)

fun getThreadName(): String = Thread.currentThread().name

fun <T> printDataWithThreadNameAndTime(t: T) = println("${getThreadName()} || ${getCurrentTime()} -> $t")

fun printError(t: Throwable?) = println("에러 : $t")

fun printComplete() = println("완료")