package io.cuesoft.apparule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.cuesoft.apparule.R;

public class DesignerRequestsRecyclerAdapter extends RecyclerView.Adapter
        <DesignerRequestsRecyclerAdapter.RequestsViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;

    public DesignerRequestsRecyclerAdapter(Context context) {
     this.mContext = context;
     this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflating the layout for Categories View
        View mItemView = mInflater.inflate(R.layout.requests_item,parent, false);
        return new RequestsViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class RequestsViewHolder extends RecyclerView.ViewHolder {
        public RequestsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
