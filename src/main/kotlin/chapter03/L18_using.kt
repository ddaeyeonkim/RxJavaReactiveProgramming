package chapter03

import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import java.util.concurrent.Callable

// 리소스를 관리하는 using 메서드
fun main() {
    Flowable.using<Int, Int>(
        Callable { 0 },                               // 리소스 객체 획득
        Function { i -> Flowable.just(i) },           // 통지 처리 작업을 수행
        Consumer { i -> 0 }                           // 받은 리소스를 해제
    )
        .subscribe { data -> println(data) }
}