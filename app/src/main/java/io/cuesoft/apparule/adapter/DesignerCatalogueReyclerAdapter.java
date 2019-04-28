package io.cuesoft.apparule.adapter;

import android.content.Context;
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
import io.cuesoft.apparule.model.DesignerCatalogueRecyclerModel;
import io.cuesoft.apparule.service.GlideApp;

public class DesignerCatalogueReyclerAdapter extends RecyclerView.Adapter<DesignerCatalogueReyclerAdapter.CatalogueViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<DesignerCatalogueRecyclerModel> mCatalogueData;

    public DesignerCatalogueReyclerAdapter(Context mContext,
                                           ArrayList<DesignerCatalogueRecyclerModel> mCatalogueData){
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mCatalogueData = mCatalogueData;
    }

    @NonNull
    @Override
    public CatalogueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mCatalogueView = mInflater.inflate(R.layout.catalogoue_item, parent , false);
        return new CatalogueViewHolder(mCatalogueView);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogueViewHolder holder, int position) {
        DesignerCatalogueRecyclerModel catalogueModel = mCatalogueData.get(position);
        holder.bindTo(catalogueModel);
    }

    @Override
    public int getItemCount() {
        return mCatalogueData.size();
    }


    class CatalogueViewHolder extends RecyclerView.ViewHolder
                    implements View.OnClickListener{

        private TextView itemCatalogueText;
        private TextView descriptionCatalogue;
        private TextView timeCatalogue;
        private TextView priceCatalogue;
        private ImageView itemCatalogueImage;

       public CatalogueViewHolder(View itemView)
        {
            super(itemView);

            itemCatalogueText = itemView.findViewById(R.id.catalogue_title);
            descriptionCatalogue = itemView.findViewById(R.id.catalogue_description);
            timeCatalogue = itemView.findViewById(R.id.catalogue_time);
            priceCatalogue = itemView.findViewById(R.id.catalogue_price);
            itemCatalogueImage = itemView.findViewById(R.id.catalogue_image);
        }

        void bindTo(DesignerCatalogueRecyclerModel catalogueModel){
            //Populate the textView with data.
            itemCatalogueText.setText(catalogueModel.getItemCatalogueText());
            descriptionCatalogue.setText(catalogueModel.getDescriptionCatalogue());
            timeCatalogue.setText(catalogueModel.getTimeCatalogue());
            priceCatalogue.setText(catalogueModel.getPriceCatalogue());

            GlideApp.with(mContext).load(
                    catalogueModel.getImageCatalogue()).into(itemCatalogueImage);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
