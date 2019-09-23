package chapter04

import io.reactivex.Flowable
import java.math.BigDecimal

/*
main || 03:36.153 ->  1
main || 03:36.155 ->  0.1
main || 03:36.155 ->  1
main || 03:36.155 -> 완료
 */
fun main() {
    Flowable.just("1", "1.0", "0.1", "0.10", "1")
        .distinctUntilChanged { data1, data2 ->
            val convert1 = BigDecimal(data1)
            val convert2 = BigDecimal(data2)
            convert1.compareTo(convert2) == 0
        }
        .subscribe(DebugSubscriber())
}