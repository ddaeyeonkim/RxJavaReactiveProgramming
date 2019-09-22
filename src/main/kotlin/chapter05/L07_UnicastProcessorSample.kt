package chapter05

import chapter04.DebugSubscriber
import io.reactivex.processors.UnicastProcessor

/*
main || 20:19.533 -> No.1 1
main || 20:19.535 -> No.1 2
Subscriber No.2 추가
main || 20:19.535 -> 에러 : java.lang.IllegalStateException: This processor allows only a single Subscriber
main || 20:19.535 -> No.1 3
main || 20:19.535 -> 완료
 */
fun main() {

    // 1 개의 소비자만 구독 가능
    // 다른 소비자가 구독시 IllegalStateException 에러 통지
    val processor = UnicastProcessor.create<Int>()

    processor.subscribe(DebugSubscriber("No.1"))

    processor.onNext(1)
    processor.onNext(2)

    println("Subscriber No.2 추가")
    processor.subscribe(DebugSubscriber("No.2"))

    processor.onNext(3)

    processor.onComplete()
}