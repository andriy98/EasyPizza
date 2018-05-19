package com.example.andrii.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Order_fragment extends Fragment {
    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle("Оформлення");
    }

    private static final int LAYOUT = R.layout.order_new_version;
    private View view;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter_city;
    private ArrayAdapter<String> adapter_numb;
    private String name,size,price,count;
    private int all_price = 0;
    private String [] sizes;
    private String [] prices;
    private String [] cities = {"Стебник", "Трускавець", "Дрогобич"};
    private String [] numbers = {"1","2","3","4","5","6","7","8","9","10"};
    private Button button_book, button_cancel;
    private DBHelp_price dbHelpPrice;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        dbHelpPrice = new DBHelp_price(getContext());
        button_book = (Button) view.findViewById(R.id.button_book);
        //button_cancel = (Button) view.findViewById(R.id.button_cancel);
        //name="";
        //price="";
        //size="";
        //count="";
        final HashMap<String,String> emails = new HashMap<>();
        emails.put("\"Челентано\"","rtdmytryshyn@gmail.com");
        emails.put("\"Велика Тарілка\"","rtdmytryshyn@gmail.com");
        emails.put("\"Фелічіта\"","sdmytryshyn67@gmail.com");
        final Cursor data = dbHelpPrice.getAllData();
        final HashSet<String> hashSet = new HashSet<>();
        while (data.moveToNext()){
            hashSet.add(data.getString(6));
            Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
            Matcher matcher=pat.matcher(data.getString(4));
            while (matcher.find()) {
                all_price+=Integer.parseInt(matcher.group());
            }
           //name = name + data.getString(1) + ", ";
           //price = price + data.getString(4) + ", ";
           //size = size + data.getString(3) + "; ";
           //count = count + data.getInt(5) +"; ";
           //Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
           //Matcher matcher=pat.matcher(data.getString(4));
           //while (matcher.find()) {
           //    all_price+=Integer.parseInt(matcher.group());
           //}
        }
        final String [] pizzeries = hashSet.toArray(new String[0]);
        final EditText textAddress = (EditText) view.findViewById(R.id.edit_address);
        final EditText textCity = (EditText) view.findViewById(R.id.edit_city);
        final EditText textPhone = (EditText) view.findViewById(R.id.edit_telephone);
        final EditText textFName = (EditText) view.findViewById(R.id.edit_name);
        final EditText textPrice = (EditText) view.findViewById(R.id.edit_amount);
        textPrice.setText(all_price+" грн.");
        button_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((textFName.length()>1)&& (textAddress.length()>5)&&(textPhone.length()>5)&&(textPhone.length()<16)) {
                    for (int i = 0; i < hashSet.size(); i++) {
                        name = "";
                        price = "";
                        size = "";
                        count = "";
                        Cursor second = dbHelpPrice.getAllData();
                        while (second.moveToNext()) {
                            if (second.getString(6).equals(pizzeries[i])) {
                                name = name + second.getString(1) + ", ";
                                price = price + second.getString(4) + ", ";
                                size = size + second.getString(3) + "; ";
                                count = count + second.getInt(5) + "; ";
                            }
                        }

                        StartAsykcTask task = (StartAsykcTask) new StartAsykcTask(getContext(), emails.get(pizzeries[i].trim()), "Нове замовлення через додаток", "Назви:  " +
                                name + "\nРозміри:  " + size + "\nКількості:  " + count + "\nЦіни:  " +
                                price + "\nІм’я:  " + textFName.getText() + "\nМісто:  " + textCity.getText() +
                                "\nАдреса:  " + textAddress.getText() + "\nТелефон:  " + textPhone.getText()).execute();
                        if (i == (hashSet.size() - 1)) {
                            textFName.setText("");
                            textAddress.setText("");
                            textPhone.setText("");
                        }
                    }
                }else {
                        Toast.makeText(getContext(),"Заповніть всі поля правильно!", Toast.LENGTH_LONG).show();
                    }



                //if ((textFName.length()>1)&& (textAddress.length()>5)&&(textPhone.length()>5)&&(textPhone.length()<13)) {
                //    StartAsykcTask task = (StartAsykcTask) new StartAsykcTask(getContext(), "rtdmytryshyn@gmail.com", "Нове замовлення через додаток", "Назви:  " +
                //            name + "\nРозміри:  " + size + "\nКількості:  " + count + "\nЦіни:  " +
                //            price + "\nЗагальна ціна замовлення:  " + all_price + "\nІм’я:  " + textFName.getText() + "\nМісто:  " + spinner_city.getSelectedItem().toString() +
                //            "\nАдреса:  " + textAddress.getText() + "\nТелефон:  " + textPhone.getText()).execute();
                //    textFName.setText("");
                //    textAddress.setText("");
                //    textPhone.setText("");
                //}else {
                //    Toast.makeText(getContext(),"Заповніть всі поля правильно!", Toast.LENGTH_LONG).show();
                //}
            }
        });
        /*button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });*/
        /*TextView textDesc = (TextView) view.findViewById(R.id.text_desc1);
        final EditText textAddress = (EditText) view.findViewById(R.id.edit_address);
        final EditText textPhone = (EditText) view.findViewById(R.id.edit_phone);
        final EditText textFName = (EditText) view.findViewById(R.id.edit_name);
        final Spinner spinner_size = (Spinner) view.findViewById(R.id.spinner_size);

        final Spinner spinner_numb = (Spinner) view.findViewById(R.id.spinner_numb);

        final TextView textName = (TextView) view.findViewById(R.id.item);
        button_book = (Button) view.findViewById(R.id.button_book);
        button_cancel = (Button) view.findViewById(R.id.button_cancel);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_photo);
        final Bundle bundle = getArguments();
        sizes = bundle.getString("Size").split("/");
        prices = bundle.getString("Price").split("/");
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, sizes);
        adapter_numb = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, numbers);
        adapter_city = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_numb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        Picasso.with(getContext())
                .load(bundle.getString("Photo"))
                .into(imageView);



        textDesc.setText(bundle.getString("Desc"));
        spinner_city.setAdapter(adapter_city);
        spinner_numb.setAdapter(adapter_numb);
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
                    textFName.setText("");
                    textAddress.setText("");
                    textPhone.setText("");
                }else {
                    Toast.makeText(getContext(),"Заповніть всі поля правильно!", Toast.LENGTH_LONG).show();
                }
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() > 0 ){
                    getFragmentManager().popBackStack();
                }
            }
        });*/

        return view;
    }
}



