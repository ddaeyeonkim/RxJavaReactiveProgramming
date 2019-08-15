package chapter04

import io.reactivex.Flowable

fun main() {

    // 에러 통지
    Flowable.error<Unit>(Exception("예외 발생"))
        .subscribe(DebugSubscriber())
}