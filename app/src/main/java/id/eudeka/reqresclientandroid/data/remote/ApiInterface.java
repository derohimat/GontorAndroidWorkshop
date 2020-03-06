package id.eudeka.reqresclientandroid.data.remote;

import id.eudeka.reqresclientandroid.data.model.BaseListResponse;
import id.eudeka.reqresclientandroid.data.model.BaseResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("users/2")
    Call<BaseResponse> getUser();

    @GET("users")
    Call<BaseListResponse> getUsers();

}