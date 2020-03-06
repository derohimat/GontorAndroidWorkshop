package id.eudeka.reqresclientandroid.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import id.eudeka.reqresclientandroid.R;
import id.eudeka.reqresclientandroid.data.model.BaseListResponse;
import id.eudeka.reqresclientandroid.data.model.UserResponse;
import id.eudeka.reqresclientandroid.data.remote.ApiClient;
import id.eudeka.reqresclientandroid.data.remote.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainListActivity extends AppCompatActivity {

    private Context context = this;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ApiInterface apiInterface;

    private MainListAdapter mainListAdapter;
    private List<UserResponse> userResponseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        swipeRefreshLayout = findViewById(R.id.swipe_main);
        recyclerView = findViewById(R.id.recycler_main);

        swipeRefreshLayout.setOnRefreshListener(() -> getDataUsers());

        mainListAdapter = new MainListAdapter(context, userResponseList, item ->
                DetailActivity.start(context, item.getId())
        );
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainListAdapter);

        getDataUsers();
    }

    private void showProgress(boolean show) {
        if (show) {
            swipeRefreshLayout.setRefreshing(true);
        } else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void getDataUsers() {

        if (apiInterface == null) {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
        }

        showProgress(true);

        Call<BaseListResponse> call = apiInterface.getUsers();
        call.enqueue(new Callback<BaseListResponse>() {
            @Override
            public void onResponse(Call<BaseListResponse> call, Response<BaseListResponse> response) {
                showProgress(false);
                BaseListResponse baseResponse = response.body();
                userResponseList = baseResponse.getData();

                mainListAdapter.updateData(userResponseList);
            }

            @Override
            public void onFailure(Call<BaseListResponse> call, Throwable t) {
                showProgress(false);
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}

