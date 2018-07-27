package com.testbirds.blacklist.ui.MainActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.testbirds.blacklist.R;
import com.testbirds.blacklist.data.DataManager;
import com.testbirds.blacklist.modals.App;

import java.util.ArrayList;
import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.MyViewHolder> {
    private List<App> appList = new ArrayList<>();
    private OnAppClickedListener onAppClickedListener;

    public interface OnAppClickedListener{
        void onAppClicked(App app);
    }

    public AppAdapter() {
    }

    public void setOnAppClickedListener(OnAppClickedListener onAppClickedListener) {
        this.onAppClickedListener = onAppClickedListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        private LinearLayout llContainer;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            llContainer = view.findViewById(R.id.llContainer);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final App app = appList.get(position);
        holder.tvTitle.setText(app.getTitle());
        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAppClickedListener != null)
                {
                    onAppClickedListener.onAppClicked(app);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    public void updateList(List<App> appList)
    {
        this.appList.addAll(appList);
        notifyDataSetChanged();
    }
}
