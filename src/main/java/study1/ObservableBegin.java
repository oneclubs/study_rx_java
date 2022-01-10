package study1;

import io.reactivex.rxjava3.core.Observable;

public class ObservableBegin {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {

        Observable.just(1, 2, 3, 4, 5)
            .subscribe(System.out::println);
    }
}
