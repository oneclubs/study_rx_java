package study3;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;

public class TimerExample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws InterruptedException {

        Observable.timer(1, TimeUnit.SECONDS)
            .subscribe(value ->
                System.out.println(Thread.currentThread().getName() + " : " + value)
            );

        Thread.sleep(3000);
    }
}
