package com.example.andrii.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Basket_fragment extends Fragment {
    private static final int LAYOUT = R.layout.basket_fr;
    private View view;
    private DataBaseHelper myDB;
    private ArrayList<String> arrayName = new ArrayList<>();
    private ArrayList<String> arrayPhoto = new ArrayList<>();
    private ArrayList<String> arraySize = new ArrayList<>();
    private ArrayList<String> arrayPrice = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button create_order;
    private FragmentTransaction fragmentTransaction;
    private Bundle bundle;
    private Order_fragment order_fragment;
    private DBHelp_price dbHelpPrice;

    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle("Корзина");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
       // ListView listView = (ListView) view.findViewById(R.id.listview);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        dbHelpPrice = new DBHelp_price(getContext());
        dbHelpPrice.removeAll();
        create_order = (Button) view.findViewById(R.id.zamov);
        mLayoutManager = new LinearLayoutManager(getActivity());
        order_fragment = new Order_fragment();
        recyclerView.setLayoutManager(mLayoutManager);
        myDB = new DataBaseHelper(getContext());
        arrayName.clear();
        arrayPrice.clear();
        arraySize.clear();
        arrayPhoto.clear();
        final Cursor ted = dbHelpPrice.getAllData();
        final Cursor data = myDB.getAllData();
        if(data.getCount() == 0){
            create_order.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(), "Ваша корзина порожня !",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                System.out.println("LABEL1"+ data.getInt(0)+data.getString(1) + data.getString(2) + data.getString(3) + data.getString(4));
                arrayName.add(data.getString(1));
                arrayPhoto.add(data.getString(2));
                arraySize.add(data.getString(3));
                arrayPrice.add(data.getString(4));
                dbHelpPrice.insertData(data.getString(1),data.getString(2),data.getString(3),data.getString(4),1);
                mAdapter = new RecyclerAdapterBasket(getContext(), arrayName,arrayPhoto,arraySize,arrayPrice);
                recyclerView.setAdapter(mAdapter);
                //Custom_basket custom_basket = new Custom_basket(getContext(), arrayName,arrayPhoto,arraySize,arrayPrice);
                //ListAdapter listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,theList);
                //listView.setAdapter(custom_basket);
            }
        }
        create_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                bundle = new Bundle();
                bundle.putString("Name", "name");
                order_fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.content_main2, order_fragment).commit();
                fragmentTransaction.addToBackStack(null);*/
              //  Cursor data = dbHelpPrice.getAllData();
               // while (data.moveToNext()){
               //     System.out.println("Thursday"+data.getString(1)+data.getString(2)+data.getString(3)+data.getString(4)+data.getInt(5));
              //  }
            if (ted.getCount()>0){
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_main2, order_fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }else {
                Toast.makeText(getContext(), "Ваша корзина порожня !",Toast.LENGTH_LONG).show();
            }

                }
        });
        return view;
    }
}
