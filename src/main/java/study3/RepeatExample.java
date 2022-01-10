package study3;

import io.reactivex.rxjava3.core.Observable;

public class RepeatExample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws InterruptedException {

        Observable.just(1, 2, 3)
            .repeat(5)
            .subscribe(
                data -> System.out.println(Thread.currentThread().getName() + " : " + data)
            );

        Thread.sleep(3000);
    }
}
