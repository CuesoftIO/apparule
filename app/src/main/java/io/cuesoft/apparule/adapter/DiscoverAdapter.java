package io.cuesoft.apparule.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.interfaces.OnCategoriesClickListener;
import io.cuesoft.apparule.model.CategoriesItemModel;
import io.cuesoft.apparule.views.customer.DiscoveryActivity;

/**
 *  This is the adapter for the Categories page
 *  Showing text and imageView
 *
 * */
public class DiscoverAdapter extends
        RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder> {

    //Defining variables
    private LayoutInflater mInflater;
    Context mContext;
    private ArrayList<CategoriesItemModel> mCategoriesData;
    private OnCategoriesClickListener mOnCategoriesClickListener;
    /**
     * DiscoverAdapter Constructor initializing the categoriesModel
       @param context Context of the application
        @param mImageData ArrayList containing the Categories data
    */
    public DiscoverAdapter(Context context, ArrayList<CategoriesItemModel> mImageData, OnCategoriesClickListener onCategoriesClickListener){
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.mCategoriesData = mImageData;
        this.mOnCategoriesClickListener = onCategoriesClickListener;
    }


    @NonNull
    @Override
    public DiscoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflating the layout for Categories View
        View mItemView = mInflater.inflate(R.layout.categories_page, parent, false);
        return new DiscoverViewHolder(mItemView);
    }

    //Adding Views for RecyclerViews
    @Override
    public void onBindViewHolder(@NonNull DiscoverViewHolder holder, int position) {

        CategoriesItemModel currentItems = mCategoriesData.get(position);
        holder.bindTo(currentItems);
    }

    //The itemSize for the RecyclerView
    @Override
    public int getItemCount() {
        return mCategoriesData.size();
    }

    /**
     * ViewHolder class that represents 
     */

    public class DiscoverViewHolder extends RecyclerView.ViewHolder
                    implements  View.OnClickListener{
        private ImageView imageView;
        private TextView textView;
        private DiscoveryActivity discoverObject;

        public DiscoverViewHolder(View itemView) {
            super(itemView);
           //Initializing textView and ImageView
            imageView= itemView.findViewById(R.id.discover_imageView);
            textView = itemView.findViewById(R.id.categories_textView);
            itemView.setOnClickListener(this);

        }

        void bindTo(CategoriesItemModel currentItems){
            //Adding the images for imageView in categories
            Glide.with(mContext)
                    .load(currentItems.getImageResources()).into(imageView);
            //Set the text for Categories TextView
            textView.setText(currentItems.getCategoriesText());
        }

        @Override
        public void onClick(View v) {
            CategoriesItemModel currentModel = mCategoriesData.get(getAdapterPosition());
            String categoriesText = currentModel.getCategoriesText();
            mOnCategoriesClickListener.onCategoriesClick(categoriesText);
        }
    }
}
