package chapter01

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/*
RxComputationThreadPool-1 : Hello, World!
RxComputationThreadPool-1 : 안녕, RxJava!
RxComputationThreadPool-1 : 완료
 */
fun main() {
    val flowable = Flowable.create(
        object : FlowableOnSubscribe<String> {
            override fun subscribe(emitter: FlowableEmitter<String>) {
                val dataArray = arrayOf("Hello, World!", "안녕, RxJava!")

                for (data in dataArray) {
                    // 구독이 해지되면 처리를 중단한다
                    if (emitter.isCancelled) {
                        return
                    }

                    // 데이터를 통지한다
                    emitter.onNext(data)
                }

                // 완료를 통지한다
                emitter.onComplete()
            }
        },
        BackpressureStrategy.BUFFER     // 초과한 데이터는 버퍼링한다
    )

    flowable
        .observeOn(Schedulers.computation())        // Subscriber 처리를 개별 스레드에서 실행한다
        .subscribe(                                 // 구독한다
            object : Subscriber<String> {
                // 데이터 개수 요청과 구독 해지를 하는 객체
                private var subscription: Subscription? = null

                // 구독 시작 시 처리
                override fun onSubscribe(s: Subscription?) {
                    this.subscription = s                   // Subscription 을 Subscriber 에 보관한다
                    this.subscription?.request(1L)      // 받을 데이터 개수를 요청한다
                }

                // 데이터를 받을 때 처리
                override fun onNext(t: String?) {
                    val threadName = Thread.currentThread().name        // 실행 중인 스레드 이름을 얻는다
                    println("$threadName : $t")                         // 받은 데이터를 출력한다
                    this.subscription?.request(1L)
                }

                // 완료 통지시 처리
                override fun onComplete() {
                    val threadName = Thread.currentThread().name        // 실행 중인 스레드 이름을 얻는다
                    println("$threadName : 완료")
                }

                // 에러 통지시 처리
                override fun onError(t: Throwable?) {
                    t?.printStackTrace()
                }
            }
        )

    Thread.sleep(500L)      // 잠시 기다린다
}