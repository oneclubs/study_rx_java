package study4;

import common.PrintUtils;
import io.reactivex.rxjava3.core.Observable;
import java.util.Arrays;

public class MapExample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {

        Observable.just(1, 2, 3, 4, 5)
            .map(x -> x * 2)
            .subscribe(System.out::println);

        PrintUtils.line();
        PrintUtils.line();

        Observable.fromIterable(
                Arrays.asList(
                    Arrays.asList(1, 2),
                    Arrays.asList(3, 4)
                ))
            .flatMap(Observable::fromIterable)
            .subscribe(System.out::println);

    }
}
