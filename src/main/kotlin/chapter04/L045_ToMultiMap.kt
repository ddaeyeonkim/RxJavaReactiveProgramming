package chapter04

import io.reactivex.Flowable
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.LinkedHashMap

/*
RxComputationThreadPool-1 || 36:06.912 ->  {짝수=[0, 2, 4], 홀수=[1, 3]}
 */
fun main() {
    Flowable.interval(500L, TimeUnit.MILLISECONDS)
        .take(5)
        .toMultimap(
            // keySelector
            { data -> if (data.toInt() % 2 == 0) "짝수" else "홀수" },
            // valueSelector
            { data -> data.toString() },
            // mapSupplier
            { LinkedHashMap() },
            // collectionFactory, default ArrayList
            { LinkedList() }
        )
        .subscribe(DebugSingleObserver())

    Thread.sleep(2600L)
}