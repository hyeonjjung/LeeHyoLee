package com.project.hci.ajou.leehyolee;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Calendar extends Fragment {
    public static Calendar newInstance() {
        return new Calendar();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }
}
