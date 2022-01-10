package study1;

import common.PrintUtils;
import io.reactivex.rxjava3.core.Observable;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class ObservableFrom {

    @SuppressWarnings({"ResultOfMethodCallIgnored", "BlockingMethodInNonBlockingContext"})
    public static void main(String[] args) {

        execute("fromArray", () ->

            Observable.fromArray(1, 2, 3, 4, 5)
                .subscribe(System.out::println)
        );
        execute("fromIterable", () ->

            Observable.fromIterable(Arrays.asList(1, 2, 3, 4, 5))
                .subscribe(System.out::println)
        );
        execute("fromCallable", () ->

            Observable.fromCallable(() -> {
                Thread.sleep(1000);
                return 1;
            }).subscribe(System.out::println)

        );
        execute("fromFuture", () ->

            Observable.fromFuture(CompletableFuture.supplyAsync(() -> {
                System.out.println("Completable Future");
                return 1;
            })).subscribe(System.out::println)
        );
    }

    private static void execute(String tag, Runnable runnable) {
        PrintUtils.bar(tag);
        runnable.run();
        PrintUtils.line();
    }
}
