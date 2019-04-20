package io.cuesoft.apparule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.model.ImageModel;
import io.cuesoft.apparule.service.GlideApp;

public class SecondMainAdapter extends
        RecyclerView.Adapter<SecondMainAdapter.MainViewHolder> {

    private LayoutInflater mInflater;
    Context mContext;
    private ArrayList<ImageModel> mItemsData;


    public SecondMainAdapter(Context context,ArrayList<ImageModel> ItemsData){
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.mItemsData = ItemsData;
    }

    @NonNull
    @Override
    public SecondMainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.main_page_item, parent,false);
        return new SecondMainAdapter.MainViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondMainAdapter.MainViewHolder holder, int position) {
        ImageModel currentItems = mItemsData.get(position);
        holder.bindTo(currentItems);
    }

    @Override
    public int getItemCount() {
        return mItemsData.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {


        private ImageView imageView;
        private ImageView profileImageView;

        MainViewHolder(View itemView)
        {
            super(itemView);
            profileImageView = itemView.findViewById(R.id.profilePic);
            imageView = itemView.findViewById(R.id.itemsImageView);

        }

        void bindTo(ImageModel currentItems){

            // Load the images into the ImageView using the Glide library.
            GlideApp.with(mContext)
                    .load(
                            currentItems.getImage())
                    .apply(new RequestOptions().override(1800, 600))
                    .into(imageView);

            GlideApp.with(mContext)
                    .load(currentItems.getImage()).apply(new RequestOptions().circleCrop())
                    .into(profileImageView);
        }


    }

    }

