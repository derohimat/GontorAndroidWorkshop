package id.eudeka.reqresclientandroid.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

public class DetailActivity extends AppCompatActivity {

    public static final String EXT_ID = "DetailActivity.Id";

    private Context context = this;
    private TextView txtName;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;
    private int selectedUserId;

    public static void start(Context context, int userId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXT_ID, userId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtName = findViewById(R.id.txt_name);

        selectedUserId = getIntent().getIntExtra(EXT_ID, 0);
        getDataUser();
    }

    private void showProgress(boolean show) {
        if (show) {
            progressDialog = new ProgressDialog(DetailActivity.this);
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

        Call<BaseResponse> call = apiInterface.getUser(selectedUserId);
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
