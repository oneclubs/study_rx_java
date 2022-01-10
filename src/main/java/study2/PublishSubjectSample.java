package study2;

import common.PrintUtils;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class PublishSubjectSample {

    @SuppressWarnings({"ResultOfMethodCallIgnored", "DuplicatedCode"})
    public static void main(String[] args) {

        // subscribe 이후만
        PublishSubject<Integer> pub1 = PublishSubject.create();
        pub1.onNext(1);
        pub1.onNext(2);
        pub1.subscribe(value -> System.out.println("[1] -> " + value)); // 3, 4, 5
        pub1.onNext(3);
        pub1.onNext(4);
        pub1.subscribe(value -> System.out.println("[2] -> " + value)); // 5
        pub1.onNext(5);
        pub1.onComplete();
        pub1.onNext(6); // no print

        PrintUtils.line();
        PrintUtils.line();

        PublishSubject<Integer> pub2 = PublishSubject.create();
        pub2.subscribe(System.out::println); // nothing
        pub2.onComplete();

        PrintUtils.line();
        PrintUtils.line();

        PublishSubject<Integer> pub3 = PublishSubject.create();
        pub3.subscribe(System.out::println);

        Observable.range(1, 5)
            .subscribe(pub3); // 1, 2, 3, 4, 5
    }
}
