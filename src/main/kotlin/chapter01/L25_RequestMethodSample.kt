package chapter01

import io.reactivex.Flowable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

// onSubscribe 메서드에서 request 메서드를 호출하는 예제
/*
onSubscribe start
onSubscribe end
1
2
3
완료
 */
fun main() {
    Flowable
        .just(1, 2, 3)
        .subscribe(
            object : Subscriber<Int> {
                override fun onSubscribe(s: Subscription?) {
                    println("onSubscribe start")
                    s?.request(Long.MAX_VALUE)
                    println("onSubscribe end")
                }

                override fun onNext(t: Int?) {
                    println(t)
                }

                override fun onComplete() {
                    println("완료")
                }

                override fun onError(t: Throwable?) {
                    println("에러 : $t")
                }
            }
        )
}