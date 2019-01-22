package io.cuesoft.apparule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.model.ItemsModel;

public class DiscoverAdapter extends
        RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder> {

    private LayoutInflater mInflater;
    Context mContext;
    private ArrayList<ItemsModel>mImageData;

    public DiscoverAdapter(Context context, ArrayList<ItemsModel> mImageData){
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.mImageData = mImageData;
    }

    @NonNull
    @Override
    public DiscoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.categories_page, parent, false);
        return new DiscoverViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverViewHolder holder, int position) {
        ItemsModel currentItems = mImageData.get(position);
        holder.bindTo(currentItems);
    }


    @Override
    public int getItemCount() {
        return mImageData.size();
    }

    public class DiscoverViewHolder extends RecyclerView.ViewHolder
                    implements  View.OnClickListener{
        private ImageView imageView;

        public DiscoverViewHolder(View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.discover_imageView);
            itemView.setOnClickListener(this);
        }

        void bindTo(ItemsModel currentItems){
            Glide.with(mContext)
                    .load(currentItems.getImage()).into(imageView);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
