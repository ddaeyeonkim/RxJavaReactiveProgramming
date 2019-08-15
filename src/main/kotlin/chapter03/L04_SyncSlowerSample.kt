package chapter03

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

// 데이터를 받는 측이 무거운 처리 작업을 하는 예제
/*
emit : 1565861577675 밀리초 0
emit : 1565861579676 밀리초 1
emit : 1565861581676 밀리초 2
 */
fun main() {

    Flowable.interval(1000L, TimeUnit.MILLISECONDS)
        // 데이터를 통지할 때의 시스템 시각을 출력한다
        .doOnNext { data -> println("emit : ${System.currentTimeMillis()} 밀리초 $data") }
        //구독한다
        .subscribe { Thread.sleep(2000L) }      // 무거운 처리 작업을 한다고 가정한다
                                                         // 생산자가 500 밀리초 작업에 영향을 받지 않는다
    Thread.sleep(5000L)
}