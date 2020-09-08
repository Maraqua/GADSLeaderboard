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


import com.girlschema.gadsleaderboard.adapter.SkillIQRecyclerAdapter;
import com.girlschema.gadsleaderboard.databinding.FragmentSkillIqBinding;

import com.girlschema.gadsleaderboard.model.SkillIQModel;

import com.girlschema.gadsleaderboard.network.ApiInterface;
import com.girlschema.gadsleaderboard.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillIqFragment extends Fragment {
    private FragmentSkillIqBinding mSkillIQBinding;
    private SkillIQRecyclerAdapter mSkillIQRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mSkillIQBinding = FragmentSkillIqBinding.inflate(inflater,container, false);
        View view = mSkillIQBinding.getRoot();
        /*Create handle for the RetrofitInstance interface*/
        ApiInterface skillIQData = RetrofitClientInstance.getRetrofitInstance().getClient().create(ApiInterface.class);
        Call<List<SkillIQModel>> call = skillIQData.getSkillIQList();
        call.enqueue(new Callback<List<SkillIQModel>>() {
            @Override
            public void onResponse(Call<List<SkillIQModel>> call, Response<List<SkillIQModel>> response) {
                initializeDisplayContent(response.body());
            }

            @Override
            public void onFailure(Call<List<SkillIQModel>> call, Throwable t) {

                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void initializeDisplayContent(List<SkillIQModel> skillIQModelList) {
        final RecyclerView recyclerView = mSkillIQBinding.skillBoard;
        mSkillIQRecyclerAdapter = new SkillIQRecyclerAdapter(getActivity(),skillIQModelList);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mSkillIQRecyclerAdapter);


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mSkillIQBinding = null;
    }
}