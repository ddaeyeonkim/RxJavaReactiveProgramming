package chapter01

import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

// CompositeDisposable 예제
/*
no.1: 1
no.2: 1
no.1 canceled
no.2 canceled
 */
fun main() {
    // Disposable 을 합친다
    val compositeDisposable = CompositeDisposable()

    compositeDisposable.add(
        Flowable.range(1, 3)
            .doOnCancel { println("no.1 canceled") }
            .observeOn(Schedulers.computation())
            .subscribe {
                Thread.sleep(100L)
                println("no.1: $it")
            }
    )

    compositeDisposable.add(
        Flowable.range(1, 3)
            .doOnCancel { println("no.2 canceled") }
            .observeOn(Schedulers.computation())
            .subscribe {
                Thread.sleep(100L)
                println("no.2: $it")
            }
    )

    Thread.sleep(150L)

    compositeDisposable.dispose()
}