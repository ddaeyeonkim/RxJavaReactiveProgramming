package chapter03

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

// 유효한 스케줄러 예제
/*
RxComputationThreadPool-1 : 1
RxComputationThreadPool-1 : 2
RxComputationThreadPool-1 : 3
RxComputationThreadPool-1 : 4
RxComputationThreadPool-1 : 5
 */
fun main() {

    Flowable.just(1, 2, 3, 4, 5)
        .subscribeOn(Schedulers.computation())      // RxComputationThreadPool
        .subscribeOn(Schedulers.io())               // RxCachedThreadScheduler
        .subscribeOn(Schedulers.single())           // RxSingleScheduler
        .subscribe { data ->
            run {
                val threadName = Thread.currentThread().name
                println("$threadName : $data")
            }
        }

    Thread.sleep(500)
}