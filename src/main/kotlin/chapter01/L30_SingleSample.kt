package chapter01

import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.time.DayOfWeek
import java.time.LocalDate

// single 예제
/*
THURSDAY
 */
fun main() {

    val single = Single.create<DayOfWeek> { emitter ->
        emitter.onSuccess(LocalDate.now().dayOfWeek)
    }

    single.subscribe(
        object : SingleObserver<DayOfWeek?> {
            override fun onSubscribe(d: Disposable) {
                // 아무것도 하지 않는다
            }

            override fun onSuccess(t: DayOfWeek) {
                println(t)
            }

            override fun onError(e: Throwable) {
                println("에러 : $e")
            }
        }
    )
}