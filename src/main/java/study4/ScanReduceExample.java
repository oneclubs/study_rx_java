package study4;

import common.PrintUtils;
import io.reactivex.rxjava3.core.Observable;

public class ScanReduceExample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {

        Observable.just(1, 2, 3, 4, 5)
            .scan(Integer::sum)
            .subscribe(System.out::println); // 1 3 6 10 15

        PrintUtils.line();
        PrintUtils.line();

        Observable.just(1, 2, 3, 4, 5)
            .reduce(Integer::sum)
            .subscribe(System.out::println); // 15
    }
}
