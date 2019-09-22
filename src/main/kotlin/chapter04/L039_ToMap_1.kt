package chapter04

import io.reactivex.Flowable

/*
main || 26:58.837 ->  {1=1D, 2=2E, 3=3C}
 */
fun main() {
    Flowable.just("1A", "2B", "3C", "1D", "2E")
        // 데이터로 생성한 키와 데이터 쌍을 담은 Map 을 통지
        .toMap { data -> data.substring(0, 1).toLong() }
        .subscribe(DebugSingleObserver())
}