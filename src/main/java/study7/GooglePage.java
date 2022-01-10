package study7;

import common.PrintUtils;
import common.ThreadUtils;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.security.sasl.RealmCallback;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

public class GooglePage {

    private final GoogleInterface google;

    GooglePage() {


        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.SECONDS)
            .build();

        Retrofit retrofit = new Builder()
            .baseUrl("https://www.google.com")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build();

        this.google = retrofit.create(GoogleInterface.class);
    }


    Observable<ResponseBody> getIndex() {

        return google.get()
            .subscribeOn(Schedulers.io());
    }
}
