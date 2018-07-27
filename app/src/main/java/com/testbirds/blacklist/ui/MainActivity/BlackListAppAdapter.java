package com.testbirds.blacklist.ui.MainActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testbirds.blacklist.R;
import com.testbirds.blacklist.data.DataManager;
import com.testbirds.blacklist.modals.App;

import java.util.ArrayList;
import java.util.List;

public class BlackListAppAdapter extends RecyclerView.Adapter<BlackListAppAdapter.MyViewHolder> {
    private List<App> appList = new ArrayList<>();
    DataManager dataManager;

    public BlackListAppAdapter(DataManager dataManager) {
        this.dataManager = dataManager;
        updateList();
    }

    public void updateList()
    {
        appList.clear();
        appList.addAll(dataManager.getBlackListedApps());
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
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
        App app = appList.get(position);
        holder.tvTitle.setText(app.getTitle());
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

}
