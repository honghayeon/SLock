package com.example.slock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag5Floor extends Fragment {

    private View view;

    public static Frag5Floor newInstance(){
        Frag5Floor frag5Floor = new Frag5Floor();

        return frag5Floor;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_5floor, container, false);

        return  view;
    }
}
