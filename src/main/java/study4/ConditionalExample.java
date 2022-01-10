package study4;

import common.PrintUtils;
import io.reactivex.rxjava3.core.Observable;

public class ConditionalExample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {

        Observable.empty()
            .defaultIfEmpty("default value")
            .subscribe(System.out::println);

        PrintUtils.line();
        PrintUtils.line();

        Observable.range(1, 100)
            .takeWhile(i -> i < 5)
            .subscribe(System.out::println);

        PrintUtils.line();
        PrintUtils.line();

        Observable.range(1, 100)
            .takeUntil(count -> count > 5) // not value
            .subscribe(System.out::println);
    }
}
