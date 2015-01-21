package com.test.kesmile.materialdesign.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;
import com.test.kesmile.materialdesign.R;

/**
 * Created by root on 21/01/15.
 */
public class Bluetooth extends Fragment {
    private ImageView imageView;
    private RippleBackground rippleBackground;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.bluetooth_fragment_layout, container, false);
        imageView=(ImageView) rootView.findViewById(R.id.centerImage);
        rippleBackground=(RippleBackground)rootView.findViewById(R.id.content_ripple);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rippleBackground.startRippleAnimation();
            }
        });
    }
}
