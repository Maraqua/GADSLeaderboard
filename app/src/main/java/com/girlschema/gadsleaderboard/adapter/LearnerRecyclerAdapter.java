package com.girlschema.gadsleaderboard.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.girlschema.gadsleaderboard.R;
import com.girlschema.gadsleaderboard.model.LearnerModel;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearnerRecyclerAdapter extends  RecyclerView.Adapter<LearnerRecyclerAdapter.ViewHolder> {
    private final Context mContext;
    private final  List<LearnerModel> mLearnerModelList;
    private final LayoutInflater mLayoutInflater;

    public LearnerRecyclerAdapter(Context context, List<LearnerModel> learnerModelList) {
        mContext = context;
        mLearnerModelList = learnerModelList;
        mLayoutInflater = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.learners_list,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LearnerModel learnerModel = mLearnerModelList.get(position);
        holder.mLearnerNameText.setText(learnerModel.getName());
        holder.mLearnerHours.setText(String.valueOf(learnerModel.getHours())+" learning hours,");
        holder.mLearnerCountry.setText(" "+learnerModel.getCountry());

        Picasso.Builder picassoBuilder = new Picasso.Builder(mContext);
        picassoBuilder.downloader(new OkHttp3Downloader(mContext));
        picassoBuilder.build().load(mLearnerModelList.get(position).getBadgeUrl())
                .placeholder( R.drawable.progress_animation )
                .error(R.drawable.ic_launcher_background)
                .into(holder.mTopLearnerImage);
    }

    @Override
    public int getItemCount() {
        return mLearnerModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView mLearnerNameText;
        public final TextView mLearnerHours;
        public final TextView mLearnerCountry;
        public final ImageView mTopLearnerImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLearnerNameText = itemView.findViewById(R.id.learner_name);
            mLearnerHours = itemView.findViewById(R.id.learner_hours);
            mLearnerCountry = itemView.findViewById(R.id.learner_country);
            mTopLearnerImage = itemView.findViewById(R.id.top_learner);


        }
    }
}
