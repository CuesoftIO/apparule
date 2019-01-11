package io.cuesoft.apparule.adapter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.cuesoft.apparule.R;

public class MainAdapter extends
        RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private LayoutInflater mInflater;
    Context mContext;

    public MainAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.main_page_item, parent,false);
        return new MainViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MainViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {
        public MainViewHolder(View itemView) {
            super(itemView);
        }

        @Override
         public void onClick(View v) {

         }
     }
}
