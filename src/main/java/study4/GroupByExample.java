package study4;

import io.reactivex.rxjava3.core.Observable;

public class GroupByExample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {

        Observable.just(1, 2, 3, 4, 5)
            .groupBy(value -> value % 2 == 0)
            .subscribe(data -> {
                if (data == null) {
                    return;
                }

                boolean odd = Boolean.TRUE.equals(data.getKey());

                data.subscribe(value ->
                    System.out.println((odd? "Odd" : "Even") + " : " + value)
                );
            });
    }
}
