package chapter02

import java.util.*

// 불변 객체
class ImmutableObject(date : Date) {

    var value: Date? = null
        private set
        // 반환할 Date 가 외부에서 변경돼도 영향이 없도록 복제한 값을 반환한다
        get() = field?.clone() as? Date

    init {
        // 가변적인 Date 가 변경돼도 영향이 없도록 복제한 값을 설정한다
        this.value = date.clone() as? Date
    }

    // ImmutableObject 의 상태를 변경한 객체가 필요할 경우, 객체 상태를 변경하는 것이 아니라 대상 필드만 변경한 복사본을 생성하고 전달
    fun changeValue(value: Date) = ImmutableObject(value)
}

fun main() {
    val date = ImmutableObject(Date()).value
}