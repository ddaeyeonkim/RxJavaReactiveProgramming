package chapter04

import io.reactivex.Flowable

/*
main || 04:35.510 ->  11111
 */
fun main(args: Array<String>) {

    // 마지막 결과만 통지
    Flowable.just(1, 10, 100, 1000, 10000)
        .reduce(0) { sum, data -> sum + data }
        .subscribe(DebugSingleObserver())
}