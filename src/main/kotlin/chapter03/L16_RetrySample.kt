package chapter03

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

// 재시도 하는 예제
/*
subscribe : onSubscribe
flowable : doOnSubscribe
Flowable 처리 시작
data = 1
flowable : doOnSubscribe
Flowable 처리 시작
data = 1
flowable : doOnSubscribe
Flowable 처리 시작
data = 1
에러 = java.lang.Exception: 예외 발생
 */
fun main() {

    val flowable =
        Flowable.create<Int>(
            { emitter ->
                println("Flowable 처리 시작")

                for (i in 1..3) {
                    if (i == 2) {
                        throw Exception("예외 발생")
                    }

                    emitter.onNext(i)
                }

                emitter.onComplete()
            },
            BackpressureStrategy.BUFFER
        )
            .doOnSubscribe { println("flowable : doOnSubscribe") }
            .retry(2) // 에러 발생시 두 번까지 재시도, 통지 처리를 처음부터 다시 시작
//            .retry { i, _ -> i == 2 }         // predicate
//            .retry(2) { false }       // times, predicate
//            .retryUntil { false }             // booleanSupplier
//            .retryWhen { it }                 // 재시도할지를 제어하는 연산자, 데이터 통지를 늦춰 재시도 시점을 늦출 수 있다

    flowable.subscribe(
        object : Subscriber<Int?> {
            override fun onSubscribe(s: Subscription?) {
                println("subscribe : onSubscribe")
                s?.request(Long.MAX_VALUE)
            }

            override fun onNext(t: Int?) {
                println("data = $t")
            }

            override fun onComplete() {
                println("완료")
            }

            override fun onError(t: Throwable?) {
                println("에러 = $t")
            }
        }
    )
}