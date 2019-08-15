package chapter01

import io.reactivex.Maybe
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable
import java.time.DayOfWeek
import java.time.LocalDate

// Maybe 예제
/*
THURSDAY
 */
fun main() {

    val maybe = Maybe.create<DayOfWeek> { emitter ->
        emitter.onSuccess(LocalDate.now().dayOfWeek)
    }

    maybe.subscribe(
        object : MaybeObserver<DayOfWeek> {
            override fun onSubscribe(d: Disposable) {
                // 아무것도 하지 않는다
            }

            override fun onSuccess(t: DayOfWeek) {
                println(t)
            }

            override fun onComplete() {
                println("완료")
            }

            override fun onError(e: Throwable) {
                println("에러 = $e")
            }
        }
    )
}