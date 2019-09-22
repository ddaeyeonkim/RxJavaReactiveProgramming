package chapter04

import io.reactivex.Flowable

/*
main || 31:40.589 ->  {0=A, 1=D, 2=E, 3=C}
 */
fun main() {
    Flowable.just("1A", "2B", "3C", "1D", "2E")
        .toMap(
            // KeySelector : 키 생성
            { data -> data.substring(0, 1).toLong() },
            // valueSelector : 값 생성
            { data -> data.substring(1) },
            // 통지할 Map 인스턴스 생성, default HashMap
            { LinkedHashMap<Long, String>().apply { put(0, "A") } }
        )
        .subscribe(DebugSingleObserver())
}