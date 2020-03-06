package id.eudeka.reqresclientandroid.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.eudeka.reqresclientandroid.R;
import id.eudeka.reqresclientandroid.data.model.BaseResponse;
import id.eudeka.reqresclientandroid.data.model.UserResponse;
import id.eudeka.reqresclientandroid.data.remote.ApiClient;
import id.eudeka.reqresclientandroid.data.remote.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Context context = this;
    private TextView txtName;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txt_name);

        getDataUser();
    }

    private void showProgress(boolean show) {
        if (show) {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading ...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    private void getDataUser() {

        if (apiInterface == null) {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
        }

        showProgress(true);

        Call<BaseResponse> call = apiInterface.getUser();
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                showProgress(false);
                BaseResponse baseResponse = response.body();
                UserResponse userResponse = baseResponse.getData();

                txtName.setText(userResponse.getFirstName());
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                showProgress(false);
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
