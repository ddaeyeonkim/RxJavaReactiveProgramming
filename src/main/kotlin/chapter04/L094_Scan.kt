package chapter04

import io.reactivex.Flowable

/*
main || 06:27.229 ->  0
main || 06:27.231 ->  1
main || 06:27.231 ->  11
main || 06:27.231 ->  111
main || 06:27.231 ->  1111
main || 06:27.231 ->  11111
main || 06:27.231 -> 완료
 */
fun main(args: Array<String>) {

    // 중간 결과도 통지
    Flowable.just(1, 10, 100, 1000, 10000)
        .scan(0) { sum, data -> sum + data }
        .subscribe(DebugSubscriber())
}