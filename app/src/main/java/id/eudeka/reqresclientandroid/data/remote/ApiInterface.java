package id.eudeka.reqresclientandroid.data.remote;

import id.eudeka.reqresclientandroid.data.model.BaseListResponse;
import id.eudeka.reqresclientandroid.data.model.BaseResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("users/{id}")
    Call<BaseResponse> getUser(@Path("id") int id);

    @GET("users")
    Call<BaseListResponse> getUsers();

}