package study2;

import common.PrintUtils;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class BehaviorSubjectSample {

    @SuppressWarnings({"ResultOfMethodCallIgnored", "DuplicatedCode"})
    public static void main(String[] args) {

        // subscribe 시점 부터 직전 포함, 직전 없으면 default
        BehaviorSubject<Integer> behavior1 = BehaviorSubject.createDefault(77);
        behavior1.onNext(1);
        behavior1.onNext(2);
        behavior1.subscribe(value -> System.out.println("[1] -> " + value)); // with last - 2, 3, 4, 5
        behavior1.onNext(3);
        behavior1.onNext(4);
        behavior1.subscribe(value -> System.out.println("[2] -> " + value)); // with last 4, 5
        behavior1.onNext(5);
        behavior1.onComplete();
        behavior1.onNext(6); // no print

        PrintUtils.line();
        PrintUtils.line();

        BehaviorSubject<Integer> behavior2 = BehaviorSubject.createDefault(77);
        behavior2.subscribe(System.out::println); // default 77
        behavior2.onComplete();

        PrintUtils.line();
        PrintUtils.line();

        BehaviorSubject<Integer> behavior3 = BehaviorSubject.createDefault(77);
        behavior3.subscribe(System.out::println);

        Observable.range(1, 5)
            .subscribe(behavior3); // 77, 1, 2, 3, 4, 5
    }
}
