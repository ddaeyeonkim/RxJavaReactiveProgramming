package chapter05

import io.reactivex.subjects.PublishSubject

/*
main || 43:18.969 -> No.1 1
main || 43:18.971 -> No.1 2
main || 43:18.971 -> No.1 3
Subscriber No.2 추가
main || 43:18.971 -> No.1 4
main || 43:18.971 -> No.2 4
main || 43:18.971 -> No.1 5
main || 43:18.971 -> No.2 5
main || 43:18.971 -> 완료
main || 43:18.971 -> 완료
Subscriber No.3 추가
main || 43:18.971 -> 완료
 */
fun main() {

    val subject = PublishSubject.create<Int>()

    subject.onNext(1)
    subject.onNext(2)
    subject.onNext(3)
    subject.onNext(4)
    subject.onNext(5)

    subject.onComplete()

    // processor.subscribe(DebugSubscriber("No.1"))

    // println("Subscriber No.2 추가")

    // processor.subscribe(DebugSubscriber("No.2"))

    // println("Subscriber No.3 추가")
    // processor.subscribe(DebugSubscriber("No.3"))
}