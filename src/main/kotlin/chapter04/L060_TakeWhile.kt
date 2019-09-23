package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

fun main() {
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        // 지정한 조건에 해당하는 동안까지만 데이터 통지
        .takeWhile { data -> data != 3L }
        .subscribe(DebugSubscriber())

    Thread.sleep(2000L)
}