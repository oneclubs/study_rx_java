package study1;

import common.PrintUtils;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class SingleExample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {

        execute("Single", () ->
            Single.just(1)
                .subscribe(System.out::println)
        );
        execute("Single from Observable", () ->
            Single.fromObservable(
                    Observable.just(1)
//                    Observable.just(1, 2) // error
                )
                .subscribe(System.out::println)
        );
        execute("Observable just single", () ->
            Observable.just(1)
                .single(2)
                .subscribe(System.out::println) // not 2 just 1
        );
        execute("Observable empty single - default", () ->
            Observable.empty()
                .single(2)
                .subscribe(System.out::println) // 2
        );
        execute("Observable first to single", () ->
            Observable.just(1, 2, 3, 4, 5)
                .first(6)
                .subscribe(System.out::println) // not 6 1
        );
        execute("Observable take to single", () ->
            Observable.just(1, 2, 3, 4, 5)
                .take(1)
                .single(6)
                .subscribe(System.out::println) // not 6 1
        );
    }

    private static void execute(String tag, Runnable runnable) {
        PrintUtils.bar(tag);
        runnable.run();
        PrintUtils.line();
    }
}
