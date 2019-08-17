package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

fun main() {
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .take(10)
        // 완료전 1000 밀리초 동안 통지된 데이터 중 끝에서부터 2건 통지
        .takeLast(2, 1000L, TimeUnit.MILLISECONDS)
        .subscribe(DebugSubscriber())

    Thread.sleep(4000L)
}