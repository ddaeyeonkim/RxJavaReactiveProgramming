package chapter04

import io.reactivex.Flowable

fun main() {
    Flowable.fromArray("A", "B", "C", "D", "E")
        .subscribe(DebugSubscriber())

    Flowable.fromIterable(listOf("A", "B", "C", "D", "E"))
        .subscribe(DebugSubscriber())
}