package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

fun main() {
    println("시작 시각 : ${getCurrentTime()}")

    // 지정 시간 경과 후 0을 통지하는 생산자 생성
    Flowable.timer(1000L, TimeUnit.MILLISECONDS)
        .subscribe(
            ::printDataWithThreadNameAndTime,
            ::printError,
            ::printComplete
        )

    Thread.sleep(1500L)
}