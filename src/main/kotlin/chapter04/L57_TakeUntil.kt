package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

fun main() {
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        // 받은 데이터가 3이 될 때까지
        .takeUntil { data -> data == 3L }
        .subscribe(DebugSubscriber())

    Thread.sleep(2000L)
}