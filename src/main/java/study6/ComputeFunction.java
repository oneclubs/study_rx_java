package study6;

import common.PrintUtils;
import common.ThreadUtils;
import io.reactivex.rxjava3.core.Observable;
import java.util.List;

public class ComputeFunction {

    public static void compute(Integer value) {

        PrintUtils.threadLog("value : " + value);
        ThreadUtils.sleep(1000);
    }

    public static void compute(List<Integer> values) {

        PrintUtils.threadLog("values : " + values);
        ThreadUtils.sleep(1000);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void compute(Observable<Integer> observable) {

        observable.subscribe(value -> {
            PrintUtils.threadLog("values : " + value);
        });
        ThreadUtils.sleep(1000);
    }
}
