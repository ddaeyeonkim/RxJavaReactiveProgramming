package chapter03

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.concurrent.TimeUnit

// 메인 스레드가 아닌 스레드에서 처리작업을 하는 Flowable 예제
/*
start
end
RxComputationThreadPool-1 : 0
RxComputationThreadPool-1 : 1
RxComputationThreadPool-1 : 2
 */
fun main() {

    println("start")

    // 별 스레드에서 실제 처리 실행
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .subscribe(
            object : ResourceSubscriber<Long>() {
                override fun onNext(t: Long?) {
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

    // 잠시 기다린다
    Thread.sleep(1000L)
}