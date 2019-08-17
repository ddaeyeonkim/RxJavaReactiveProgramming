package chapter04

import io.reactivex.Flowable

fun main() {
    // 함수형 인터페이스에서 생성된 데이터를 통지
    Flowable.fromCallable { System.currentTimeMillis() }
        .subscribe(DebugSubscriber())
}