package com.example.andrii.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details_fragment extends Fragment {
    private static final int LAYOUT = R.layout.details;
    private View view;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        TextView textDesc = (TextView) view.findViewById(R.id.text_desc1);
        TextView textSize = (TextView) view.findViewById(R.id.text_size1);
        TextView textPrice = (TextView) view.findViewById(R.id.text_pr);
        TextView textName = (TextView) view.findViewById(R.id.item);

        ImageView imageView = (ImageView) view.findViewById(R.id.image_photo);
        Bundle bundle = getArguments();


        Picasso.with(getContext())
                .load(bundle.getString("Photo"))
                .into(imageView);



        textDesc.setText(bundle.getString("Desc"));
        textSize.setText(bundle.getString("Size"));
        textPrice.setText(bundle.getString("Price"));
        textName.setText(bundle.getString("Name"));



        return view;
    }
}
