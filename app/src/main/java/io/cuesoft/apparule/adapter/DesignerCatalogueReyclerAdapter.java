package io.cuesoft.apparule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.model.DesignerCatalogueRecyclerModel;

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
        return 9;
    }


    class CatalogueViewHolder extends RecyclerView.ViewHolder
                    implements View.OnClickListener{

        private TextView itemCatalogueText;
        private TextView descripttionCatalogue;
        private TextView

       public CatalogueViewHolder(View itemView)
        {
            super(itemView);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
