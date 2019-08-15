package chapter03

import io.reactivex.Flowable
import io.reactivex.subscribers.ResourceSubscriber

// 메인 스레드에서 작업을 처리하는 Flowable 예제
/*
start
main : 1
main : 2
main : 3
main : 완료
end
 */
fun main() {

    println("start")

    Flowable.just(1, 2, 3)
        .subscribe(
            object : ResourceSubscriber<Int>() {
                override fun onNext(t: Int?) {
                    val threadName = Thread.currentThread().name
                    println("$threadName : $t")
                }

                override fun onComplete() {
                    val threadName = Thread.currentThread().name
                    println("$threadName : 완료")
                }

                override fun onError(t: Throwable?) {
                    t?.printStackTrace()
                }

            }
        )

    println("end")
}