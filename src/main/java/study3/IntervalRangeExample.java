package study3;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;

public class IntervalRangeExample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws InterruptedException {

        Observable.intervalRange(1, 5, 1, 1, TimeUnit.SECONDS)
            .subscribe(value ->
                System.out.println(Thread.currentThread().getName() + " : " + value)
            );

        Thread.sleep(6 * 1000);
    }
}
