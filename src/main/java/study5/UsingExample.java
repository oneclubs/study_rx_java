package study5;

import common.PrintUtils;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UsingExample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws InterruptedException {

        Observable.using(
                // supplier
                () -> {
                    PrintUtils.threadLog("supplier supply data");
                    return "supplier data";
                },
                r -> Observable.create(emitter -> {
                    for (Character c : r.toCharArray()) {
                        emitter.onNext(c);
                    }
                    emitter.onComplete();
                }),
                r -> PrintUtils.threadLog("Disposed : " + r)
            )
            .observeOn(Schedulers.newThread())
            .subscribe(value -> PrintUtils.threadLog("subscribe : " + value));

        Thread.sleep(3000);
    }
}
