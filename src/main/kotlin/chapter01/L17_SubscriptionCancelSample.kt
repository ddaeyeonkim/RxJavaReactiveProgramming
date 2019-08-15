package chapter01

import io.reactivex.Flowable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit

/*
data = 0
data = 1
구독 해지
 */
fun main() {
    Flowable
        .interval(200L, TimeUnit.MILLISECONDS)
        .subscribe(
            object : Subscriber<Long> {

                private lateinit var subscription: Subscription
                private var startTime: Long = 0

                override fun onSubscribe(s: Subscription?) {
                    this.subscription = s!!
                    this.startTime = System.currentTimeMillis()
                    subscription.request(Long.MAX_VALUE)
                }

                override fun onNext(t: Long?) {
                    // 구독 시작부터 500 밀리초가 지나면 구독을 해지하고 처리를 중지한다
                    if ((System.currentTimeMillis() - startTime) > 500) {
                        subscription.cancel()       // 구독을 해지한다
                        println("구독 해지")
                        return
                    }

                    println("data = $t")
                }

                override fun onComplete() {
                    // 구독이 해지되었으므로 호출되지 않는다
                    println("onComplete")
                }

                override fun onError(t: Throwable?) {
                    println("onError")
                }
            }
        )

    Thread.sleep(2000L)
}