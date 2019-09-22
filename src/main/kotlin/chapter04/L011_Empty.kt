package chapter04

import io.reactivex.Flowable

fun main() {
    // 빈 생산자
    // 처리를 시작하면 바로 완료를 통지
    // ex) flatMap 에서 null 처리 (완료 처리)
    Flowable.empty<Unit>()
        .subscribe(DebugSubscriber())
}