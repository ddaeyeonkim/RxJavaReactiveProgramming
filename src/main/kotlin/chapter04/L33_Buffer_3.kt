package chapter04

import io.reactivex.Flowable
import io.reactivex.functions.Function
import java.util.concurrent.TimeUnit

/*
just doOnNext
main || 14:53.865 -> 1
just doOnNext
main || 14:53.869 -> 2
interval doOnNext
RxComputationThreadPool-3 || 14:54.175 -> 0
interval doOnNext
RxComputationThreadPool-3 || 14:54.475 -> 1
interval doOnNext
RxComputationThreadPool-3 || 14:54.774 -> 2
function doOnNext
function doOnNext
RxComputationThreadPool-2 || 14:54.874 -> 0
RxComputationThreadPool-1 || 14:54.874 -> 0
RxComputationThreadPool-1 || 14:54.874 ->  [0, 1, 2]
RxComputationThreadPool-1 || 14:54.875 ->  [0, 1, 2]
RxComputationThreadPool-1 || 14:54.875 -> 완료
 */
fun main() {
    // 원본 생산자
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .doOnNext { data ->
            println("interval doOnNext ")
            printDataWithThreadNameAndTime(data)
        }
        .take(6)
        .buffer(
            // openingIndicator 가장 먼저 호출, just 가 통지된 갯수만큼 반복, openingIndicator 가 종료되면 완료
            Flowable.just<Long>(1, 2)
                .doOnNext { data ->
                    println("just doOnNext ")
                    printDataWithThreadNameAndTime(data)
                },
            // closingIndicator 가 통지한 시점까지의 원본 통지 데이터를 결과로 통지
            Function<Long, Flowable<Long>> {
                Flowable.timer(1000L, TimeUnit.MILLISECONDS)
                    .doOnNext { data ->
                        println("function doOnNext ")
                        printDataWithThreadNameAndTime(data)
                    }
            }
        )
        .subscribe(DebugSubscriber())

    Thread.sleep(3000L)
}