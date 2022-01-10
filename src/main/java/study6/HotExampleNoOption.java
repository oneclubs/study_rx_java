package study6;

import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.stream.IntStream;

public class HotExampleNoOption {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {

        PublishSubject<Integer> subject = PublishSubject.create();

        subject
            .observeOn(Schedulers.computation())
            .subscribe(ComputeFunction::compute, Throwable::printStackTrace);

        IntStream.range(1, 1_000_000)
            .forEach(subject::onNext);
    }
}
