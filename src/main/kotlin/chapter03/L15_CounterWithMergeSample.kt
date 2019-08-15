package chapter03

import chapter02.Counter
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

// merge 메서드로 결합하는 예제
/*
counter.get() = 20000
 */
fun main() {

    val counter = Counter()

    val source1 =
        Flowable.range(1, 10000)
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.computation())

    val source2 =
        Flowable.range(1, 10000)
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.computation())


    // 두 Flowable 을 합친다
    // counter 객체에 대한 접근이 순차적으로 처리 된다
    Flowable.merge(source1, source2)
            .subscribe(
                { counter.increment() },
                { error -> println("에러 = $error") },
                { println("counter.get() = ${counter.get()}") }
            )

    Thread.sleep(1000L)
}