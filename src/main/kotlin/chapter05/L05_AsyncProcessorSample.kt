package chapter05

import chapter04.DebugSubscriber
import io.reactivex.processors.AsyncProcessor

/*
Subscriber No.2 추가
main || 16:13.178 -> No.1 5
main || 16:13.179 -> 완료
main || 16:13.179 -> No.2 5
main || 16:13.180 -> 완료
Subscriber No.3 추가
main || 16:13.180 -> No.3 5
main || 16:13.180 -> 완료
 */
fun main() {

    // 마지막 데이터와 완료 만 통지
    val processor = AsyncProcessor.create<Int>()

    processor.subscribe(DebugSubscriber("No.1"))

    processor.onNext(1)
    processor.onNext(2)
    processor.onNext(3)

    println("Subscriber No.2 추가")

    processor.subscribe(DebugSubscriber("No.2"))

    processor.onNext(4)
    processor.onNext(5)

    processor.onComplete()

    println("Subscriber No.3 추가")
    processor.subscribe(DebugSubscriber("No.3"))
}