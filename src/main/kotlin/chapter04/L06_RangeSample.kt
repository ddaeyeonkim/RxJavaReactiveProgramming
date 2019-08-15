package chapter04

import io.reactivex.Flowable

fun main() {

    Flowable.range(10, 3)
        .subscribe(DebugSubscriber())

    Flowable.rangeLong(10L, 3L)
        .subscribe(DebugSubscriber())
}