package com.girlschema.gadsleaderboard;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.girlschema.gadsleaderboard.databinding.FragmentSubmitBinding;


public class SubmitFragment extends DialogFragment{
    FragmentSubmitBinding mSubmitBinding;
    private AlertDialog.Builder mBuilder;
    private LayoutInflater mLayoutInflater;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mBuilder = new AlertDialog.Builder(getActivity());
        mLayoutInflater = requireActivity().getLayoutInflater();
        mBuilder.setView(mLayoutInflater.inflate(R.layout.fragment_submit,null));
        return  mBuilder.create();

    }
}