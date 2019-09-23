package chapter04

import io.reactivex.Flowable

fun main() {

    // 통지하는 데이터를 변환한 뒤 변환된 데이터를 통지
    Flowable.just("A", "B", "C", "D", "E")
        .map { data -> data.toLowerCase() }
        .subscribe(DebugSubscriber())
}