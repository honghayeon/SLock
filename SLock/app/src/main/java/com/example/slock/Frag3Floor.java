package com.example.slock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag3Floor extends Fragment {

    private View view;

    public static Frag3Floor newInstance(){
        Frag3Floor frag3Floor = new Frag3Floor();

        return frag3Floor;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_3floor, container, false);

        return  view;
    }
}
