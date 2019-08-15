package chapter01

import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

// Completable 예제
/*
완료
 */
fun main() {

    val completable = Completable.create { emitter ->
        // 중략(업무 로직 처리)

        emitter.onComplete()
    }

    completable.subscribeOn(Schedulers.computation())
        .subscribe(
            object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    // 아무것도 하지 않는다
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