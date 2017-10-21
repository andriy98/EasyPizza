package com.example.andrii.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details_fragment extends Fragment {
    private static final int LAYOUT = R.layout.details;
    private View view;
    private FragmentTransaction fragmentTransaction;
    private Bundle bundle1;
    private Order_fragment order_fragment;
    private String name,price,size,photo,desc;
    private Context context;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        order_fragment = new Order_fragment();
        final TextView textDesc = (TextView) view.findViewById(R.id.text_desc1);
        final TextView textSize = (TextView) view.findViewById(R.id.spinner_size);
        final TextView textPrice = (TextView) view.findViewById(R.id.text_pr);
        final TextView textName = (TextView) view.findViewById(R.id.item);
        Button button_book = (Button) view.findViewById(R.id.button_book);
        Button button_back = (Button) view.findViewById(R.id.button_back);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_photo);
        final Bundle bundle = getArguments();
        name = bundle.getString("Name");
        desc = bundle.getString("Desc");
        size = bundle.getString("Size");
        photo = bundle.getString("Photo");
        price = bundle.getString("Price");


        Picasso.with(getContext())
                .load(photo)
                .into(imageView);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() > 0 ){
                    getFragmentManager().popBackStack();
                } else {
                }
            }
        });


        textDesc.setText(desc);
        textSize.setText(size);
        textPrice.setText(price);
        textName.setText(name);



        button_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                bundle1 = new Bundle();
                bundle1.putString("Desc", desc);
                bundle1.putString("Size", size);
                bundle1.putString("Photo", photo);
                bundle1.putString("Name", name);
                bundle1.putString("Price", price);
                order_fragment.setArguments(bundle1);
                fragmentTransaction.replace(R.id.container, order_fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });


        return view;
    }
}
