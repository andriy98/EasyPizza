package com.example.andrii.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

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
    private Button button_book;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        TextView textDesc = (TextView) view.findViewById(R.id.text_desc1);
        final EditText textAddress = (EditText) view.findViewById(R.id.edit_address);
        final EditText textPhone = (EditText) view.findViewById(R.id.edit_phone);
        final EditText textFName = (EditText) view.findViewById(R.id.edit_name);
        final Spinner spinner_size = (Spinner) view.findViewById(R.id.spinner_size);
        final Spinner spinner_city = (Spinner) view.findViewById(R.id.spinner_city);
        final TextView textPrice = (TextView) view.findViewById(R.id.text_pr);
        TextView textName = (TextView) view.findViewById(R.id.item);
        button_book = (Button) view.findViewById(R.id.button_book);
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

        button_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((textFName.length()>1)&& (textAddress.length()>5)&&(textPhone.length()>5)&&(textPhone.length()<13)) {
                    StartAsykcTask task = (StartAsykcTask) new StartAsykcTask(getContext(), "rtdmytryshyn@gmail.com", "Нове замовлення через додаток", "Назва:  " +
                            bundle.getString("Name") + "\nРозмір:  " + spinner_size.getSelectedItem().toString() + "\nЦіна:  " +
                            textPrice.getText() + "\nІм’я:  " + textFName.getText() + "\nМісто:  " + spinner_city.getSelectedItem().toString() +
                            "\nАдреса:  " + textAddress.getText() + "\nТелефон:  " + textPhone.getText()).execute();
                }else {
                    Toast.makeText(getContext(),"Заповніть всі поля правильно!", Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }
}



