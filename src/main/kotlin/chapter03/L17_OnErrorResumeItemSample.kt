package chapter03

import io.reactivex.Flowable
import org.reactivestreams.Publisher

// 에러가 발생하면 대체 대이터를 통지하는 예제
/*
data = 100
data = 33
data = 20
data = 0
완료
 */
fun main() {

    val flowable = Flowable.just(1, 3, 5, 0, 2, 4)
        .map { data -> 100 / data }
        .onErrorReturnItem(0)
//        .onErrorReturn { t -> 0 }
//        .onErrorResumeNext(
//            Publisher { s ->
//                s.onNext(0)
//                s.onComplete()
//            }
//        )  // 에러 발생시 에러를 통지하지 않는 대신 flowable/observable 을 생성해 데이터를 통지
//        .onErrorResumeNext(Function { t -> Flowable.just(0) })
//        .onExceptionResumeNext(Flowable.just(0))
//        .onExceptionResumeNext { s ->
//            s.onNext(0)
//            s.onComplete()
//        }

    flowable.subscribe(
        { data -> println("data = $data") },
        { error -> println("error = $error") },
        { println("완료") }
    )
}