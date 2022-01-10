package study6;

import common.ThreadUtils;
import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.stream.IntStream;

public class HotExampleObservableBuffer {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {

        Flowable
            .range(1, 1_000_000)
            .onBackpressureDrop()
            .observeOn(Schedulers.computation())
            .subscribe(ComputeFunction::compute);

        ThreadUtils.sleep(10 * 1000);
    }
}
