package chapter02

import java.util.concurrent.Executors

// 2개의 스레드로 접근해 처리하는 예제
/*
13213 (원했던 20000 이 되지 않았음)
 */
class Counter {
    /**
     * volatile :
     * 업데이트한 값은 반드시 메모리에 반영돼 다른 스레드에서 참조할 때 같은 값을 얻을 수 있게 하는 제한자
     * 필드 값으로 캐시된 값이 아닌 최신 메모리 값을 가져오게 보장하는 제한자
     * 최신 값을 얻을 수만 있을 뿐 값을 업데이트할 때는 원자성을 보장하지 않는다
     * 업데이트 작업을 할 때는 하나의 특정 스레드로만 업데이트하고 그 외 스레드는 참조만 하게 해서 업데이트하는 스레드와 참조하는 스레드를 분리
      */
    @Volatile private var count = 0

    fun increment() {
        count++
    }

    fun get() = count
}

fun main() {
    val counter = Counter()

    val task = Runnable {
        // 10,000 번 계산하는 비동기 처리 작업
        for (i in 0 until 10000) {
            counter.increment()
        }
    }

    // 비동기 처리 작업 생성을 준비한다
    val executorService = Executors.newCachedThreadPool()

    // 새로운 스레드로 시작한다
    val future1 = executorService.submit(task, true)
    val future2 = executorService.submit(task, true)

    val isEnd = future1.get() and future2.get()

    if (isEnd) {
        println(counter.get())
    } else {
        println("실패")
    }

    // 비동기 처리 작업 종료
    executorService.shutdown()
}