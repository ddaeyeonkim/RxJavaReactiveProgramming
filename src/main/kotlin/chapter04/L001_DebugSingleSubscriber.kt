package chapter04

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class DebugSingleObserver<T>(
    private val label: String = ""
) : SingleObserver<T> {

    override fun onSubscribe(d: Disposable) {
        /* ignore */
    }

    override fun onSuccess(t: T) = printDataWithThreadNameAndTime("$label $t")

    override fun onError(e: Throwable) = printError(e)
}