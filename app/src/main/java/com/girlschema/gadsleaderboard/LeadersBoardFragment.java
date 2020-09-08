package com.girlschema.gadsleaderboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.girlschema.gadsleaderboard.adapter.LearnerRecyclerAdapter;
import com.girlschema.gadsleaderboard.databinding.FragmentLeadersBoardBinding;
import com.girlschema.gadsleaderboard.model.LearnerModel;
import com.girlschema.gadsleaderboard.network.LearnerData;
import com.girlschema.gadsleaderboard.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LeadersBoardFragment extends Fragment {
    private FragmentLeadersBoardBinding mLeadersBoardBinding;
    private LearnerRecyclerAdapter mLearnerRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mLeadersBoardBinding = FragmentLeadersBoardBinding.inflate(inflater,container, false);
        View view = mLeadersBoardBinding.getRoot();
        /*Create handle for the RetrofitInstance interface*/
        LearnerData learnerData = RetrofitClientInstance.getRetrofitInstance().create(LearnerData.class);
        Call<List<LearnerModel>> call = learnerData.getLearnerList();
        call.enqueue(new Callback<List<LearnerModel>>() {
            @Override
            public void onResponse(Call<List<LearnerModel>> call, Response<List<LearnerModel>> response) {
                initializeDisplayContent(response.body());
            }

            @Override
            public void onFailure(Call<List<LearnerModel>> call, Throwable t) {

//                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void initializeDisplayContent(List<LearnerModel> learnerModelList) {
            final RecyclerView recyclerView = mLeadersBoardBinding.learnersBoard;
            mLearnerRecyclerAdapter = new LearnerRecyclerAdapter(getActivity(),learnerModelList);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(mLearnerRecyclerAdapter);


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mLeadersBoardBinding = null;
    }
}