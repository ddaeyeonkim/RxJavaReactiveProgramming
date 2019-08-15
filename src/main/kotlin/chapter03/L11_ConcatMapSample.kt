package chapter03

import io.reactivex.Flowable
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

// concatMap 메서드 내에서 서로 다은 스레드로 작동하는 Flowable 을 생성하는 예제
/*
RxComputationThreadPool-1 & 58.883 : a
RxComputationThreadPool-2 & 59.893 : b
RxComputationThreadPool-3 & 00.894 : c
 */
fun main() {

    /**
     * 하나씩 순서대로 실행 (다른 스레드여도 순차적으로)
     * 원본 데이터 통지 순서대로 새로운 데이터 통지
     * 성능에 관계없이 데이터 순서가 중요할 때는 concatMap
     * 성능이 중요할 때는 concatMap 메서드 사용 X
     */
    val flowable =
        Flowable.just("a", "b", "c")
            // 받은 데이터로 flowable 을 생성하고 이 flowable 의 데이터를 통지한다
            .concatMap { data ->
                // 1000 밀리초 늦게 데이터를 통지하는 flowable 을 생성한다
                Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS)
            }

    flowable.subscribe { data ->
        val threadName = Thread.currentThread().name
        val time = LocalTime.now().format(DateTimeFormatter.ofPattern("ss.SSS"))
        println("$threadName & $time : $data")
    }

    Thread.sleep(4000L)
}