package id.eudeka.reqresclientandroid.data.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("data")
    private UserResponse data;

    public UserResponse getData() {
        return data;
    }

    @Override
    public String toString() {
        return
                "BaseResponse{" +
                        "data = '" + data + '\'' +
                        "}";
    }
}