package study7;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface GoogleInterface {

    @GET("/")
    Observable<ResponseBody> get();
}
