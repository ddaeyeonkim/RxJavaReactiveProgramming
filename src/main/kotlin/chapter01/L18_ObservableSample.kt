package chapter01

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/*
RxComputationThreadPool-1 : Hello, World!
RxComputationThreadPool-1 : 안녕, RxJava!
RxComputationThreadPool-1 : 완료
 */
fun main() {

    // 인사말을 통지하는 Observable 생성
    val observable = Observable.create(
        object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                val dataArray = arrayOf("Hello, World!", "안녕, RxJava!")

                for (data in dataArray) {
                    // 구독이 해지되면 처리를 중단한다
                    if (emitter.isDisposed) {
                        return
                    }

                    // 데이터를 통지한다
                    emitter.onNext(data)
                }

                // 완료를 통지한다
                emitter.onComplete()
            }
        }
    )

    observable
        .observeOn(Schedulers.computation())        // 소비하는 측의 처리를 개별 스레드에서 실행한다
        .subscribe(                                 // 구독한다
            object : Observer<String> {

                // subscribe 메서드 호출 시의 처리
                override fun onSubscribe(d: Disposable) {
                    // 아무것도 하지 않는다
                }

                // 데이터를 받을 때 처리
                override fun onNext(t: String) {
                    val threadName = Thread.currentThread().name        // 실행 중인 스레드 이름을 얻는다
                    println("$threadName : $t")                         // 받은 데이터를 출력한다
                    // 받은 데이터를 처리만 하고 데이터 개수 요청은 하지 않는다
                }

                // 완료 통지시 처리
                override fun onComplete() {
                    val threadName = Thread.currentThread().name        // 실행 중인 스레드 이름을 얻는다
                    println("$threadName : 완료")
                }

                // 에러 통지시 처리
                override fun onError(t: Throwable) {
                    t.printStackTrace()
                }
            }
        )

    Thread.sleep(500L)      // 잠시 기다린다
}