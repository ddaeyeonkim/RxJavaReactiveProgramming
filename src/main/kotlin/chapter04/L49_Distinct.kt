package chapter04

import io.reactivex.Flowable

fun main() {
    Flowable.just("A", "a", "B", "b", "A", "a", "B", "b")
        .distinct()
        .subscribe(DebugSubscriber())
}