package chapter06

import io.reactivex.Flowable
import org.junit.Test
import java.lang.Exception
import java.util.concurrent.TimeUnit

class L18_TestSubscriberTest {
    @Test
    @Throws(Exception::class)
    fun `TestSubscriber사용의간단한예제()`() {

        // 대상 flowable
        val target = Flowable.interval(100L, TimeUnit.MILLISECONDS)

        // 테스트 subscriber 를 생성하고 Flowable 의 처리를 시작
        val testSubscriber = target.test()

        // 아직 데이터가 통과되지 않았는지 확인
        testSubscriber.assertEmpty()

        // 지정한 시간동안 대기하게 한다
        // 비동기 처리이므로 100 밀리초로는 통지되지 않을 수 도 있다
        testSubscriber.await(150L, TimeUnit.MILLISECONDS)

        // 지금까지 통지된 데이터를 확인한다
        testSubscriber.assertValues(0L)

        testSubscriber.await(100L, TimeUnit.MILLISECONDS)

        testSubscriber.assertValues(0L, 1L)
    }

}
