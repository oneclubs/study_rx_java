package study6;

import common.ThreadUtils;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.stream.IntStream;

public class HotExampleBuffer {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {

        PublishSubject<Integer> subject = PublishSubject.create();

        subject
            .buffer(1000)
            .observeOn(Schedulers.computation())
            .subscribe(ComputeFunction::compute, Throwable::printStackTrace);

        IntStream.range(1, 1_000_000)
            .forEach(subject::onNext);

        ThreadUtils.sleep(10 * 1000);
    }
}
