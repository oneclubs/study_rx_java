package study2;

import common.PrintUtils;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.AsyncSubject;

public class AsyncSubjectSample {

    @SuppressWarnings({"ResultOfMethodCallIgnored", "DuplicatedCode"})
    public static void main(String[] args) {

        // 언제나 마지막
        AsyncSubject<Integer> async1 = AsyncSubject.create();
        async1.onNext(1);
        async1.onNext(2);
        async1.subscribe(value -> System.out.println("[1] -> " + value)); // 5
        async1.onNext(3);
        async1.onNext(4);
        async1.subscribe(value -> System.out.println("[2] -> " + value)); // 5
        async1.onNext(5);
        async1.onComplete();
        async1.onNext(6); // no print

        PrintUtils.line();
        PrintUtils.line();

        AsyncSubject<Integer> async2 = AsyncSubject.create();
        async2.subscribe(System.out::println); // nothing
        async2.onComplete();

        PrintUtils.line();
        PrintUtils.line();

        AsyncSubject<Integer> async3 = AsyncSubject.create();
        async3.subscribe(System.out::println);

        Observable.range(1, 100)
            .subscribe(async3); // only 100
    }
}
