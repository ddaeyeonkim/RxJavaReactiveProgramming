package chapter02

import java.util.concurrent.atomic.AtomicInteger

// 원자성 : 일련의 동작을 외부에서 분할 할 수 없는 특성
// (읽기,변경,쓰기) 의 원자성을 보장하는 클래스의 패키지 : java.util.concurrent.atomic
class AtomicCounter {

    private var count = AtomicInteger(0)

    fun increment() {
        count.incrementAndGet()
    }

    fun get() = count.get()
}