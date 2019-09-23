package chapter05

import chapter04.DebugSubscriber
import io.reactivex.processors.BehaviorProcessor

/*
main || 08:27.832 -> No.1 1
main || 08:27.833 -> No.1 2
main || 08:27.833 -> No.1 3
Subscriber No.2 추가
main || 08:27.833 -> No.2 3
main || 08:27.833 -> No.1 4
main || 08:27.833 -> No.2 4
main || 08:27.833 -> No.1 5
main || 08:27.833 -> No.2 5
main || 08:27.835 -> 완료
main || 08:27.835 -> 완료
Subscriber No.3 추가
main || 08:27.835 -> 완료
 */
fun main() {

    // 마지막 통지 데이터 캐싱
    val processor = BehaviorProcessor.create<Int>()

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