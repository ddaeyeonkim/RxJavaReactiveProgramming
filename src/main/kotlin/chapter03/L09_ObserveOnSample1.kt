package chapter03

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

// observeOn 메서드로 bufferSize 를 지정하는 예제
/*
RxComputationThreadPool-1 : 0
RxComputationThreadPool-1 : 4
RxComputationThreadPool-1 : 8
RxComputationThreadPool-1 : 12
RxComputationThreadPool-1 : 16
 */
fun main() {
    val flowable =
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // BackpressureMode.DROP 을 설정했을 때와 마찬가지로 작동한다
            .onBackpressureDrop()

    flowable
        // 비동기로 데이터를 받게 하고, 버퍼 크기를 1 로 설정한다
        .observeOn(Schedulers.computation(), false, 1)
//      .observeOn(Schedulers.computation(), false, 2)        // 2 건씩
        .subscribe(
            object : ResourceSubscriber<Long>() {
                override fun onNext(t: Long?) {
                    // 무거운 처리 작업을 한다고 가정하고 1000 밀리초를 기다린다
                    try {
                        Thread.sleep(1000L)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                        // 에러가 발생해 종료한다
                        exitProcess(1)
                    }

                    println("${Thread.currentThread().name} : $t")
                }

                override fun onComplete() {
                    println("onComplete")
                }

                override fun onError(t: Throwable?) {
                    println("onError")
                }

            }
        )

    Thread.sleep(7000L)
}