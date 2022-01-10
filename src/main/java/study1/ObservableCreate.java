package study1;

import io.reactivex.rxjava3.core.Observable;

public class ObservableCreate {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {

        Observable.create(emitter -> {

            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();

        }).subscribe(System.out::println);
    }
}
