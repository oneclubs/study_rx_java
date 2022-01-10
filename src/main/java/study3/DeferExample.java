package study3;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableFromCallable;
import java.util.concurrent.TimeUnit;

public class DeferExample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws InterruptedException {

        // defer 는 subscribe 하기 전까지 데이터 흐름을 실행하지 않음
        Observable<Integer> observable = Observable.defer(() ->
            observer -> {
                observer.onNext(1);
                observer.onNext(2);
                observer.onComplete();
            }
        );

        observable.subscribe(
            System.out::println
        );
    }
}
