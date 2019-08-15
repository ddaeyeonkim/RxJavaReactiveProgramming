package chapter04

import io.reactivex.subscribers.DisposableSubscriber

class DebugSubscriber<T>(
    private val label: String = ""
) : DisposableSubscriber<T>() {

    override fun onNext(t: T) = printDataWithThreadNameAndTime("$label $t")

    override fun onComplete() = printComplete()

    override fun onError(t: Throwable?) = printError(t)
}