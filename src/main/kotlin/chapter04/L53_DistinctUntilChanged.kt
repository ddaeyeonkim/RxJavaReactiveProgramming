package chapter04

import io.reactivex.Flowable

/*
main || 55:20.708 ->  A
main || 55:20.710 ->  a
main || 55:20.710 ->  A
main || 55:20.710 ->  a
main || 55:20.710 -> 완료
 */
fun main() {
    Flowable.just("A", "a", "a", "A", "a")
        // 연속된 같은 값을 제외하고 통지한다
        .distinctUntilChanged()
        .subscribe(DebugSubscriber())
}