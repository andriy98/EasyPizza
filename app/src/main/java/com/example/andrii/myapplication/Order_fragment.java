package com.example.andrii.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Order_fragment extends Fragment {
    private static final int LAYOUT = R.layout.order;
    private View view;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter_city;
    private String [] sizes;
    private String [] prices;
    private String [] cities = {"Стебник", "Трускавець", "Дрогобич"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        TextView textDesc = (TextView) view.findViewById(R.id.text_desc1);
        Spinner spinner_size = (Spinner) view.findViewById(R.id.spinner_size);
        Spinner spinner_city = (Spinner) view.findViewById(R.id.spinner_city);
        final TextView textPrice = (TextView) view.findViewById(R.id.text_pr);
        TextView textName = (TextView) view.findViewById(R.id.item);

        ImageView imageView = (ImageView) view.findViewById(R.id.image_photo);
        final Bundle bundle = getArguments();
        sizes = bundle.getString("Size").split("/");
        prices = bundle.getString("Price").split("/");
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, sizes);
        adapter_city = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, cities);


        Picasso.with(getContext())
                .load(bundle.getString("Photo"))
                .into(imageView);



        textDesc.setText(bundle.getString("Desc"));
        spinner_city.setAdapter(adapter_city);
        spinner_size.setAdapter(adapter);
        spinner_size.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textPrice.setText(prices[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        textName.setText(bundle.getString("Name"));




        return view;
    }
}



