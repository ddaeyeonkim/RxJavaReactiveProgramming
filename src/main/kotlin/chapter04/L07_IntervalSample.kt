package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

fun main() {
    Flowable.interval(1000L, TimeUnit.MILLISECONDS)
//    Flowable.interval(3000L, 1000L, TimeUnit.MILLISECONDS, Schedulers.single())
//        .take(3)        // 횟수 제한
        .doOnSubscribe { println("doOnSubscribe : ${getCurrentTime()}") }
        .subscribe(::printDataWithThreadNameAndTime)

    Thread.sleep(5000L)
}