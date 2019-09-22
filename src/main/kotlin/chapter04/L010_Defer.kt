package chapter04

import io.reactivex.Flowable
import java.time.LocalTime

/*
main || 31:02.426 -> No.1 02:31:02.415
완료
main || 31:04.431 -> No.2 02:31:04.431
완료
 */
fun main() {

    /**
     * 구독시마다 통지된 현재 시간이 다름 -> 새로 생성한다는 의미
     */

    // 구독이 발생할 때마다 함수형 인터페이스로 정의한 새로운 생산자를 생성
    val flowable = Flowable.defer { Flowable.just(LocalTime.now()) }

    flowable.subscribe(DebugSubscriber("No.1"))

    Thread.sleep(2000L)

    flowable.subscribe(DebugSubscriber("No.2"))
}