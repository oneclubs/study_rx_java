package study3;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;

public class IntervalExample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws InterruptedException {

        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe(value ->
                System.out.println(Thread.currentThread().getName() + " : " + value)
            );

        Thread.sleep(5000);
    }
}
