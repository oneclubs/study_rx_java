package study1;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class ObservableDispose {

    public static void main(String[] args) {

        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);

        Disposable disposable = observable.subscribe(System.out::println);

        // onCompleted then
        System.out.println(disposable.isDisposed());
    }
}
