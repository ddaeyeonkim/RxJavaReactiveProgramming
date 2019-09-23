package chapter04

import io.reactivex.Flowable

/*
main || 11:09.921 ->  A
main || 11:09.923 ->  B
main || 11:09.923 ->  C
main || 11:09.923 ->  A
main || 11:09.923 ->  B
main || 11:09.923 ->  C
main || 11:09.923 -> 완료
 */
fun main(args: Array<String>) {

    Flowable.just("A", "B", "C")
        .repeat(2)
        .subscribe(DebugSubscriber())
}