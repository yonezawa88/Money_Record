package com.example.moneyrecord.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.moneyrecord.R;


public class Calendarfragment extends Fragment {
    public Calendarfragment() {
        // 空のコンストラクタ（必須）
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // このFragmentに使うレイアウトを指定する
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }
}