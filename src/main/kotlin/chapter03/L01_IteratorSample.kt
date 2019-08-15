package chapter03

// iterator 패턴
fun main() {

    val list = listOf("a", "b", "c")
    val iterator = list.iterator()

    while (iterator.hasNext()) {
        val value = iterator.next()
        println(value)
    }
}