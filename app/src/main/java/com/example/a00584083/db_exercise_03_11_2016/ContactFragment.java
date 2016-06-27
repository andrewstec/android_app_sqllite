package com.example.a00584083.db_exercise_03_11_2016;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Development on 3/11/2016.
 */
public class ContactFragment extends PreferenceFragment{

    LinearLayout linearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contact_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onClickDelete(View view) {
        System.out.print(view.getTag());
    }

    public void onClickUpdate(View view) {
        System.out.print(view.getTag());
    }

}
