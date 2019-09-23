package chapter05

import chapter04.DebugSubscriber
import io.reactivex.processors.ReplayProcessor

/*
main || 14:10.150 -> No.1 1
main || 14:10.151 -> No.1 2
main || 14:10.151 -> No.1 3
Subscriber No.2 추가
main || 14:10.151 -> No.2 1
main || 14:10.151 -> No.2 2
main || 14:10.151 -> No.2 3
main || 14:10.151 -> No.1 4
main || 14:10.152 -> No.2 4
main || 14:10.152 -> No.1 5
main || 14:10.152 -> No.2 5
main || 14:10.152 -> 완료
main || 14:10.152 -> 완료
Subscriber No.3 추가
main || 14:10.152 -> No.3 1
main || 14:10.152 -> No.3 2
main || 14:10.152 -> No.3 3
main || 14:10.152 -> No.3 4
main || 14:10.152 -> No.3 5
main || 14:10.152 -> 완료
 */
fun main() {

    // 모두 또는 지정한 범위만큼 캐싱
    val processor = ReplayProcessor.create<Int>()

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