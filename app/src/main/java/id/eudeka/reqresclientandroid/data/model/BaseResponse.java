package id.eudeka.reqresclientandroid.data.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("data")
    private UserResponse userResponse;

    public UserResponse getData() {
        return userResponse;
    }

    @Override
    public String toString() {
        return
                "BaseResponse{" +
                        "data = '" + userResponse + '\'' +
                        "}";
    }
}