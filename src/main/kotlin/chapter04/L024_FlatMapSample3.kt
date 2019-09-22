package chapter04

import io.reactivex.Flowable

/*
main || 29:15.008 ->  10
main || 29:15.010 ->  5
main || 29:15.011 ->  -1
완료
 */
fun main(args: Array<String>) {
    Flowable.just(1, 2, 0, 4, 5)
        .map { data -> 10 / data }
        .flatMap(
            // 일반 통지 시 데이터
            { data -> Flowable.just(data) },
            // 에러 발생 시 데이터
            { error -> Flowable.just(-1) },
            // 완료 시 데이터
            // 원본 flowable 이 완료를 통지한 것이 아니므로 onCompleteSupplier 의 함수형 인터페이스는 실행되지 않음
            { Flowable.just(100) }
        )
        .subscribe(DebugSubscriber())
}