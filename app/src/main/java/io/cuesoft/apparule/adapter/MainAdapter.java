package io.cuesoft.apparule.adapter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.model.ItemsModel;
import io.cuesoft.apparule.views.ProportionalImageView;

public class MainAdapter extends
        RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private LayoutInflater mInflater;
    Context mContext;
    private ArrayList<ItemsModel> mItemsData;

    public MainAdapter(Context context,ArrayList<ItemsModel> ItemsData){
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.mItemsData = ItemsData;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.main_page_item, parent,false);
        return new MainViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            ItemsModel currentItems = mItemsData.get(position);
            holder.bindTo(currentItems);
    }

    @Override
    public int getItemCount() {
        return mItemsData.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {

        private ProportionalImageView imageView;

         public MainViewHolder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemsImageView);
            itemView.setOnClickListener(this);
        }

        void bindTo(ItemsModel currentItems){

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext)
                    .load(
                    currentItems.getImage()).into(imageView);
        }

        @Override
         public void onClick(View v) {
         }
     }
}
