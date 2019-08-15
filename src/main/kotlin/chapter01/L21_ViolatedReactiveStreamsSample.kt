package chapter01

import io.reactivex.Flowable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

// onSubscribe 메서드 처리 도중에 다른 처리가 시작되는 예제
/*
onSubscribe: start
onSubscribe: end
1
2
3
완료
*/
fun main() {
    Flowable.range(1, 3)
        .subscribe(
            object : Subscriber<Int> {
                override fun onSubscribe(s: Subscription?) {
                    println("onSubscribe: start")
                    s?.request(Long.MAX_VALUE)
                    println("onSubscribe: end")
                }

                // onSubscribe 메서드가 끝난 뒤에 onNext 메서드 실행
                override fun onNext(t: Int?) {
                    println(t)
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