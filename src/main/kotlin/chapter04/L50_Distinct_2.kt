package chapter04

import io.reactivex.Flowable

/*
main || 17:46.556 ->  A
main || 17:46.558 ->  B
main || 17:46.558 -> 완료
 */
fun main() {
    Flowable.just("A", "a", "B", "b", "A", "a", "B", "b")
        // 대소문자 관계없이 같은 데이터를 제외하고 통지한다
        .distinct { data -> data.toLowerCase() }
        .subscribe(DebugSubscriber())
}