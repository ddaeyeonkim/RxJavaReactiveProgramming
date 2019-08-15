package chapter03

import io.reactivex.Flowable

fun main() {
    val list = listOf("a", "b", "c")
    val flowable = Flowable.fromIterable(list)
//    val flowable = list.toFlowable()

    // 처리를 시작한다
    flowable.subscribe { println(it) }
}