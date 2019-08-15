package chapter02

import io.reactivex.functions.Action

// this 출력 예제 (람다식과 익명 클래스의 this 비교)
/*
익명 클래스의 this : chapter02.DifferenceOfThisSample$execute$anonymous$1@4617c264
람다식의 this     : chapter02.DifferenceOfThisSample@36baf30c
 */

fun main() {
    val target = DifferenceOfThisSample()
    target.execute()
}

class DifferenceOfThisSample {

    fun execute() {

        val anonymous = object : Action {
            override fun run() {
                println("익명 클래스의 this : $this")
            }
        }

        val lambda = { println("람다식의 this : $this") }

        // 각각 실행
        // 익명 클래스의 this 는 구현한 인터페이스의 인스턴스를 나타냄
        anonymous.run()
        // 람다식의 this 는 람다식을 구현한 클래스의 인스턴스를 나타냄
        lambda.invoke()
    }
}
