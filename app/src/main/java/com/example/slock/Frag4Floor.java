package com.example.slock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag4Floor extends Fragment {

    private View view;

    public static Frag4Floor newInstance(){
        Frag4Floor frag4Floor = new Frag4Floor();

        return frag4Floor;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_4floor, container, false);

        return  view;
    }
}
