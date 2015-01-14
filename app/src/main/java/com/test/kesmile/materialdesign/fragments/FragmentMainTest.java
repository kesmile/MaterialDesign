package com.test.kesmile.materialdesign.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.test.kesmile.materialdesign.R;

/**
 * Created by root on 8/01/15.
 */
public class FragmentMainTest extends Fragment {
    private ListView lista;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);
        lista = (ListView) rootView.findViewById(R.id.lst_fragment);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] data = new String[]{"uno","dos","tres","cuatro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                                               android.R.layout.simple_list_item_1,data);
        lista.setAdapter(adapter);
    }
}
