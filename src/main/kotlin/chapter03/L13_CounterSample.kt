package chapter03

import chapter02.Counter
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

// 두 스레드에서 같은 객체의 변경 작업을 수행하는 예제
/*
counter.get() = 19147
counter.get() = 19515
 */
fun main() {

    val counter = Counter()

    Flowable.range(1, 10000)
        .subscribeOn(Schedulers.computation())
        .observeOn(Schedulers.computation())
        .subscribe(
            { counter.increment() },
            { error -> println("에러 = $error")},
            { println("counter.get() = ${counter.get()}")}
        )

    Flowable.range(1, 10000)
        .subscribeOn(Schedulers.computation())
        .observeOn(Schedulers.computation())
        .subscribe(
            { counter.increment() },
            { error -> println("에러 = $error")},
            { println("counter.get() = ${counter.get()}")}
        )

    Thread.sleep(1000L)
}