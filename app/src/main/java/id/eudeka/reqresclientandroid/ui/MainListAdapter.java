package id.eudeka.reqresclientandroid.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.eudeka.reqresclientandroid.R;
import id.eudeka.reqresclientandroid.data.model.UserResponse;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {
    private final Context context;
    private List<UserResponse> userResponseList;
    private final OnItemClickListener listener;

    public MainListAdapter(Context context, List<UserResponse> userResponseList, OnItemClickListener itemClickListener) {
        this.context = context;
        this.userResponseList = userResponseList;
        this.listener = itemClickListener;
    }

    public void updateData(List<UserResponse> newData) {
        userResponseList = newData;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(UserResponse item);
    }

    @Override
    public void onBindViewHolder(@NonNull MainListAdapter.ViewHolder holder, int position) {
        final UserResponse userData = userResponseList.get(position);
        holder.bind(context, userData, listener);
    }

    @NonNull
    @Override
    public MainListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return userResponseList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgUser;
        TextView txtName;
        TextView txtEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.img_user);
            txtName = itemView.findViewById(R.id.txt_name);
            txtEmail = itemView.findViewById(R.id.txt_email);
        }

        public void bind(Context context, final UserResponse item, final OnItemClickListener listener) {

            txtName.setText(item.getFirstName() + " " + item.getLastName());
            txtEmail.setText(item.getEmail());

            Glide.with(context).load(item.getAvatar()).into(imgUser);

            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }
}
