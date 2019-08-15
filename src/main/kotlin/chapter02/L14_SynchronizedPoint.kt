package chapter02

// synchronized 블록을 사용하게 변경한 Point 클래스
class SynchronizedPoint {

    // lock 객체 : 처리의 소유권을 얻으려는 대상이 되는 객체
    // 락을 소유한 스레드만이 synchronized 블록에 들어가 작업을 실행할 수 있다
    private val lock = Any()

    // AtomicInteger -> Int 로 변경된 이유
    // synchronized 블록에서 나올 때 값이 메모리에 업데이트돼,
    // 다음에 다른 스레드가 synchronized 블록에 들어갈 때 그 값이 메모리에서 반영되기 때문
    private var x: Int = 0
    private var y: Int = 0

    fun rightUp() {
        synchronized (lock) {
            x++
            y++
        }
    }

    // 반환값이 참조형 객체일 경우 복사본을 전달해야함
    fun getX() = synchronized(lock) { x }
    fun getY() = synchronized(lock) { y }
}