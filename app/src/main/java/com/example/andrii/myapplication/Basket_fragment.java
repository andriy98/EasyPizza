package com.example.andrii.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
<<<<<<< HEAD
import android.widget.Button;
=======
>>>>>>> eaed8754ab8c26a00865546921fe17c2d06cb1f5
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Basket_fragment extends Fragment {
    private static final int LAYOUT = R.layout.basket_fr;
    private View view;
    private DataBaseHelper myDB;
    private ArrayList<String> arrayName = new ArrayList<>();
    private ArrayList<String> arrayPhoto = new ArrayList<>();
    private ArrayList<String> arraySize = new ArrayList<>();
    private ArrayList<String> arrayPrice = new ArrayList<>();
<<<<<<< HEAD
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button create_order;

    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle("Корзина");
    }
=======


>>>>>>> eaed8754ab8c26a00865546921fe17c2d06cb1f5

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
<<<<<<< HEAD
       // ListView listView = (ListView) view.findViewById(R.id.listview);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        create_order = (Button) view.findViewById(R.id.zamov);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
=======
        ListView listView = (ListView) view.findViewById(R.id.listview);
>>>>>>> eaed8754ab8c26a00865546921fe17c2d06cb1f5
        myDB = new DataBaseHelper(getContext());
        arrayName.clear();
        arrayPrice.clear();
        arraySize.clear();
        arrayPhoto.clear();
        Cursor data = myDB.getAllData();
        if(data.getCount() == 0){
<<<<<<< HEAD
            create_order.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(), "Ваша корзина порожня !",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                System.out.println("LABEL1"+ data.getInt(0)+data.getString(1) + data.getString(2) + data.getString(3) + data.getString(4));
=======
            Toast.makeText(getContext(), "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                System.out.println("LABEL1"+ data.getString(1) + data.getString(2) + data.getString(3) + data.getString(4));
>>>>>>> eaed8754ab8c26a00865546921fe17c2d06cb1f5
                arrayName.add(data.getString(1));
                arrayPhoto.add(data.getString(2));
                arraySize.add(data.getString(3));
                arrayPrice.add(data.getString(4));
<<<<<<< HEAD
                mAdapter = new RecyclerAdapterBasket(getContext(), arrayName,arrayPhoto,arraySize,arrayPrice);
                recyclerView.setAdapter(mAdapter);
                //Custom_basket custom_basket = new Custom_basket(getContext(), arrayName,arrayPhoto,arraySize,arrayPrice);
                //ListAdapter listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,theList);
                //listView.setAdapter(custom_basket);
=======
                Custom_basket custom_basket = new Custom_basket(getContext(), arrayName,arrayPhoto,arraySize,arrayPrice);
                //ListAdapter listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(custom_basket);
>>>>>>> eaed8754ab8c26a00865546921fe17c2d06cb1f5
            }
        }

        return view;
    }
}
