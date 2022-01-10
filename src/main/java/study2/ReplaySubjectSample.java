package study2;

import common.PrintUtils;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class ReplaySubjectSample {

    @SuppressWarnings({"ResultOfMethodCallIgnored", "DuplicatedCode"})
    public static void main(String[] args) {

        // 처음부터 전부 -> 메모리 누수 생길수 있음
        ReplaySubject<Integer> rep1 = ReplaySubject.create();
        rep1.onNext(1);
        rep1.onNext(2);
        rep1.subscribe(value -> System.out.println("[1] -> " + value)); // 1, 2, 3, 4, 5
        rep1.onNext(3);
        rep1.onNext(4);
        rep1.subscribe(value -> System.out.println("[2] -> " + value)); // 1, 2, 3, 4, 5
        rep1.onNext(5);
        rep1.onComplete();
        rep1.onNext(6); // no print

        PrintUtils.line();
        PrintUtils.line();

        ReplaySubject<Integer> rep2 = ReplaySubject.create();
        rep2.subscribe(System.out::println); // nothing
        rep2.onComplete();

        PrintUtils.line();
        PrintUtils.line();

        ReplaySubject<Integer> rep3 = ReplaySubject.create();
        rep3.subscribe(System.out::println);

        Observable.range(1, 5)
            .subscribe(rep3); // 1, 2, 3, 4, 5
    }
}
