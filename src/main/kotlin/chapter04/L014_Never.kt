package chapter04

import io.reactivex.Flowable

fun main() {

    // 아무것도 통지 X
    Flowable.never<Unit>()
        .subscribe(DebugSubscriber())
}