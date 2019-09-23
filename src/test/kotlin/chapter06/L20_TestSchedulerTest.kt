package chapter06

import io.reactivex.Flowable
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import java.util.concurrent.TimeUnit

class L20_TestSchedulerTest {

    @Test
    fun `TestScheduler실행`() {

        val start = System.currentTimeMillis()

        val testScheduler = TestScheduler()

        val flowable = Flowable.interval(500L, TimeUnit.MILLISECONDS, testScheduler)

        val result = flowable.test()

        println("data = ${result.values()}")
        result.assertEmpty()

        testScheduler.advanceTimeBy(500L, TimeUnit.MILLISECONDS)

        println("data = ${result.values()}")

        testScheduler.advanceTimeBy(500L, TimeUnit.MILLISECONDS)

        println("data = ${result.values()}")
        result.assertValues(0L, 1L)

        testScheduler.advanceTimeTo(2000L, TimeUnit.MILLISECONDS)

        println("data = ${result.values()}")
        result.assertValues(0L, 1L, 2L, 3L)

        println("testScheduler#now= ${testScheduler.now(TimeUnit.MILLISECONDS)}")

        val totalTime = System.currentTimeMillis() - start
        println("테스트에 걸린 시간 = ${totalTime}")
    }
}