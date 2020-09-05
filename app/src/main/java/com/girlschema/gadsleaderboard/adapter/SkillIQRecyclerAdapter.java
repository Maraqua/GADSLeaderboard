package com.girlschema.gadsleaderboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.girlschema.gadsleaderboard.R;
import com.girlschema.gadsleaderboard.model.SkillIQModel;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SkillIQRecyclerAdapter extends RecyclerView.Adapter<SkillIQRecyclerAdapter.ViewHolder> {
    private  final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<SkillIQModel> mSkillIQModelList;

    public SkillIQRecyclerAdapter(Context context, List<SkillIQModel> skillIQModelList) {
        mContext = context;
        mSkillIQModelList = skillIQModelList;
        mLayoutInflater = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.skilliq_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    SkillIQModel skillIQModel = mSkillIQModelList.get(position);
    holder.mSkillName.setText(skillIQModel.getName());
        holder.mSkillScore.setText(String.valueOf(skillIQModel.getScore())+ " skill IQ score,");
        holder.mSkillCountry.setText(" "+skillIQModel.getCountry());

        Picasso.Builder picassoBuilder = new Picasso.Builder(mContext);
        picassoBuilder.downloader(new OkHttp3Downloader(mContext));
        picassoBuilder.build().load(mSkillIQModelList.get(position).getBadgeUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.mSkillBadge);
    }

    @Override
    public int getItemCount() {
        return mSkillIQModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       public final TextView mSkillName;
       public final TextView mSkillScore;
       public final TextView mSkillCountry;
       public final ImageView mSkillBadge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mSkillName = itemView.findViewById(R.id.skilliq_name);
            mSkillScore = itemView.findViewById(R.id.skill_score);
            mSkillCountry = itemView.findViewById(R.id.skill_country);
            mSkillBadge = itemView.findViewById(R.id.top_skill_badge);

        }
    }
}
