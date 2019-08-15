package chapter03

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

// flatMap 메서드 내에서 서로 다른 스레드에서 작동하는 Flowable 을 생성하는 예제
/*
RxComputationThreadPool-2&1565875263332 : b
RxComputationThreadPool-3&1565875263333 : a
RxComputationThreadPool-3&1565875263333 : c
 */
fun main() {
    /**
     * 실행 결과를 보면 통지된 데이터 순서가 원래 데이터를 받은 순서와 다르다
     * 실행 순서와 상관없이 처리 성능이 중요할 때는 flatMap 메서드 사용.
     * 데이터 순서가 중요하다면 flatMap 메서드 사용 X
     */
    val flowable = Flowable.just("a", "b", "c")
        // 받은 데이터로 flowable 을 생성하고 이 flowable 의 데이터를 통지한다
        .flatMap { data ->
            // 1000 밀리초 늦게 데이터를 통지하는 flowable 을 생성한다
            Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS)
        }

    flowable.subscribe { data -> println("${Thread.currentThread().name} & ${System.currentTimeMillis()} : $data") }

    Thread.sleep(2000L)
}