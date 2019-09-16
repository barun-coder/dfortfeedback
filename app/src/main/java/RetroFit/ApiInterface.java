package RetroFit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

/**
 * Created by prashantm on 10/27/2016.
 */
public interface ApiInterface {

    @GET
    Call<ResponseBody> getData(@Url String remainingURL, @Header("x-api-key") String session_key);


}


