package chapter04

import io.reactivex.Flowable

/*
main || 20:46.099 ->  [1, 2, 3, 4, 5]
 */
fun main() {
    Flowable.just(1, 2, 3, 4, 5)
        .toList()
        .subscribe(DebugSingleObserver())
}