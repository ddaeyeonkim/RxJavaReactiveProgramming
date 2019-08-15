package chapter03

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit

// MissingBackpressureException 이 발생하는 예제
/*
emit: 0
emit: 1
~~~~~
emit: 12
emit: 13
waiting......
emit: 14
emit: 15
~~~~~
emit: 126
emit: 127
received: 0
에러 = io.reactivex.exceptions.MissingBackpressureException: Can't deliver value 128 due to lack of requests
 */
fun main() {
    val flowable =
        Flowable.interval(10L, TimeUnit.MICROSECONDS)
            .doOnNext { value -> println("emit: $value") }

    flowable.observeOn(Schedulers.computation())
        .subscribe(
            object : Subscriber<Long?> {
                override fun onSubscribe(s: Subscription?) {
                    s?.request(Long.MAX_VALUE)
                }

                override fun onNext(t: Long?) {
                    try {
                        println("waiting......")
                        Thread.sleep(1000L)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    println("received: $t")
                }

                override fun onComplete() {
                    println("종료")
                }

                override fun onError(t: Throwable?) {
                    println("에러 = $t")
                }
            }
        )

    Thread.sleep(5000L)
}