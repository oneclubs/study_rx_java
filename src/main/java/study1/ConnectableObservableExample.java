package study1;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class ConnectableObservableExample {

    public static void main(String[] args) {

        ConnectableObservable<Integer> observable =
            ConnectableObservable.just(1, 2, 3, 4, 5)
                .publish();
        Disposable disposable = observable.subscribe(System.out::println);

        System.out.println(disposable.isDisposed());

        observable.connect();

        System.out.println(disposable.isDisposed());
    }
}
