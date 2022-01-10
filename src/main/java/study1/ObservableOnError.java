package study1;

import io.reactivex.rxjava3.core.Observable;

public class ObservableOnError {

    public static void main(String[] args) {

        // Expected OnErrorNotImplementedException
        Observable.create(emitter -> {

            for (int i = 0; i < 10; i++) {
                if (i == 3) {
                    throw new RuntimeException("error");
                }

                emitter.onNext(i);
            }
        }).subscribe(
            value -> System.out.println("subscribe onNext : " + value)
        );
    }
}
