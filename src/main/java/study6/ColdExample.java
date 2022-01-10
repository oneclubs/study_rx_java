package study6;

import common.ThreadUtils;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ColdExample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {

        Observable.range(1, 1_000_000)
            .observeOn(Schedulers.computation())
            .subscribe(ComputeFunction::compute);

        ThreadUtils.sleep(7 * 1000);
    }
}
