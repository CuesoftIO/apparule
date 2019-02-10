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
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.model.DesignerRequestsRecyclerModel;

public class DesignerRequestsRecyclerAdapter extends RecyclerView.Adapter
        <DesignerRequestsRecyclerAdapter.RequestsViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<DesignerRequestsRecyclerModel>  mReqeustsData;

    public DesignerRequestsRecyclerAdapter(Context context,
                   ArrayList<DesignerRequestsRecyclerModel> mReqeustsData) {
     this.mContext = context;
     this.mInflater = LayoutInflater.from(context);
     this.mReqeustsData = mReqeustsData;
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
        DesignerRequestsRecyclerModel requestsModel = mReqeustsData.get(position);
        holder.bindTo(requestsModel);
    }

    @Override
    public int getItemCount() {
        return mReqeustsData.size();
    }

    public class RequestsViewHolder extends RecyclerView.ViewHolder {

        private TextView mCustomersRequestsName;
        private TextView mItemRequestsTitle;
        private TextView mRequestsTime;
        private ImageView mCustomersRequestsImage;
        private ImageView mItemRequestsImage;


        public RequestsViewHolder(View itemView) {
            super(itemView);
            mCustomersRequestsName = itemView.findViewById(R.id.requests_customers_name);
            mItemRequestsTitle = itemView.findViewById(R.id.requests_item_title);
            mRequestsTime = itemView.findViewById(R.id.requests_time);
            mCustomersRequestsImage = itemView.findViewById(R.id.requests_customer_image);
            mItemRequestsImage = itemView.findViewById(R.id.requests_item_image);
        }

        public void bindTo(DesignerRequestsRecyclerModel requestsModel) {
        mCustomersRequestsName.setText(requestsModel.getCustomerRequestName());
        mItemRequestsTitle.setText(requestsModel.getItemRequestTitle());
        mRequestsTime.setText(requestsModel.getItemRequestTime());

        Glide.with(mContext).load(
                    requestsModel.getCustomerRequestImage())
                .apply(new RequestOptions().circleCrop()).into(mCustomersRequestsImage);

        Glide.with(mContext).load(
                    requestsModel.getItemRequestImage()).into(mItemRequestsImage);
        }
    }
}
