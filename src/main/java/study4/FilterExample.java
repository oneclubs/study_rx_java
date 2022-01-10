package study4;

import io.reactivex.rxjava3.core.Observable;

public class FilterExample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {

        Observable.just(1, 2, 3, 4, 5)
            .filter(value -> value % 3 == 0)
            .subscribe(System.out::println);
    }
}
