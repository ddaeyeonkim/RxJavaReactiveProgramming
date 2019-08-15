package chapter02

// synchronized method 로 변환한 예제
class SynchronizedPoint2 {

    private var x: Int = 0
    private var y: Int = 0

    @Synchronized fun rightUp() {
        x++
        y++
    }

    @Synchronized fun getX() = x
    @Synchronized fun getY() = y
}