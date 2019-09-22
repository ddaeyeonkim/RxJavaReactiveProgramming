package chapter04

import io.reactivex.Flowable

fun main() {

    Flowable.just("a", "b", "c", "d", "e")
        .subscribe(DebugSubscriber())
}