package study7;

import common.PrintUtils;
import common.ThreadUtils;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.io.IOException;
import okhttp3.ResponseBody;

public class RetrofitSample {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws IOException {

        PrintUtils.threadLog("before call google");

        new GooglePage().getIndex()
            .subscribe(data -> {
                PrintUtils.threadLog("get from google");
                PrintUtils.threadLog(data.string());
            });

        ThreadUtils.sleep(1000);
        PrintUtils.threadLog("waiting from google 2s");
        ThreadUtils.sleep(2000);
        PrintUtils.threadLog("waiting done");
        System.exit(0);

    }
}
