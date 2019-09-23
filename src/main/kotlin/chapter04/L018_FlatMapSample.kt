package chapter04

import io.reactivex.Flowable

/*
main || 30:49.219 ->  a
main || 30:49.221 ->  b
main || 30:49.222 ->  c
완료
 */
fun main(args: Array<String>) {

    Flowable.just("A", "", "B", "", "C")
        .flatMap { data ->
            if ("" == data) {
                // 비어있음
                Flowable.empty()
            } else {
                // 소문자
                Flowable.just(data.toLowerCase())
            } }
        .subscribe(DebugSubscriber())
}