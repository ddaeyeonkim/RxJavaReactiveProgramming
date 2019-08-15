package chapter03

import io.reactivex.Flowable
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

// concatMapEager 메서드 내에서 서로 다른 스레드로 작동하는 Flowable 을 생성하는 예제
/*
RxComputationThreadPool-3 & 05.039 : a
RxComputationThreadPool-3 & 05.051 : b
RxComputationThreadPool-3 & 05.051 : c
 */
fun main() {
    /**
     * flatMap + concatMap
     * 동시 실행 후, 원본 데이터 순서대로 버퍼에 넣는다
     * 데이터 순서와 성능 모두 중요한 경우에 사용
     * 통지 전까지 데이터를 버퍼에 쌓아야 해서 대량의 데이터인 경우 메모리가 부족해질 위험성 있음
     */
    val flowable =
        Flowable.just("a", "b", "c")
            // 받은 데이터로 flowable 을 생성하고 이 flowable 의 데이터를 통지한다
            .concatMapEager { data ->
                // 1000 밀리초 늦게 데이터를 통지하는 flowable 을 생성한다
                Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS)
            }

    flowable.subscribe { data ->
        val threadName = Thread.currentThread().name
        val time = LocalTime.now().format(DateTimeFormatter.ofPattern("ss.SSS"))
        println("$threadName & $time : $data")
    }

    Thread.sleep(2000L)
}