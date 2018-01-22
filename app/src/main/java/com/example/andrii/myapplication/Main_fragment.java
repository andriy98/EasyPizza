package com.example.andrii.myapplication;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Main_fragment extends Fragment {
    private DatabaseReference myRef;
    private ListView listView;
    private ArrayList<String> array_descr = new ArrayList<>();
    private ArrayList<String> array_photo = new ArrayList<>();
    private ArrayList<String> array_price = new ArrayList<>();
    private ArrayList<Boolean> array_check = new ArrayList<>();
    private ArrayList<Boolean> array_check_second = new ArrayList<>();
    private ArrayList<Boolean> array_radio = new ArrayList<>();
    private Map<String, String> data;
    private ArrayList<String> array_sizes = new ArrayList<>();
    private ArrayList<String> array_names = new ArrayList<>();
    private static final int LAYOUT = R.layout.pizzas_fr;
    private View view;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DataBaseHelper myDB;
    private String[] strings,strings_1;
    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle("Піца");
        getPizzas();
        progressDialog = ProgressDialog.show(getContext(),"Завантаження","Зачекайте, будь ласка...",false,false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
       // listView = (ListView) view.findViewById(R.id.discr_for_task);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        myDB = new DataBaseHelper(getContext());
        myRef = FirebaseDatabase.getInstance().getReference();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        return view;
    }


    public void getPizzas(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Pizzas");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                array_descr.clear();
                array_photo.clear();
                array_sizes.clear();
                array_names.clear();
                array_price.clear();
                array_check.clear();
                array_check_second.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    final String name = ds.getKey();
                    myRef.child("Pizzas").child(ds.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            data = (Map<String, String>) dataSnapshot.getValue();
                            array_descr.add(data.get("Опис:"));
                            array_sizes.add(data.get("Розміри:"));
                            array_price.add(data.get("Ціна"));
                            array_photo.add(data.get("Фото"));
<<<<<<< HEAD
                            Cursor cursor = myDB.getAllData();
                            if(cursor.getCount() == 0){
                                System.out.println("No");
                                array_check.add(false);
                                array_check_second.add(false);
                            }else{
                                strings = data.get("Розміри:").split("-");
                                strings_1 = data.get("Ціна").split("-");
                                while(cursor.moveToNext()){
                                    if ((cursor.getString(1).equals(name)==true)&&(cursor.getString(4).equals(strings[1])==true)){
                                        array_check.add(true);
                                        System.out.println("YES");
                                    }else if ((cursor.getString(1).equals(name)==true)&&(cursor.getString(4).equals(strings_1[1])==true)){
                                        array_check_second.add(true);
                                        System.out.println("YES2");
                                    }else {
                                        array_check_second.add(false);
                                        array_check.add(false);
                                        System.out.println("NO2");
                                    }
                                }
                            }
                            array_radio.add(false);
                            array_names.add(name);
                            System.out.println("atata"+array_check);
                            CustomListAdapter adapter = new CustomListAdapter(getContext(),array_descr,array_names, array_sizes,array_price,array_photo,array_check,array_radio,array_check_second);
                            //listView.setAdapter(adapter);
                            mAdapter = new RecyclerAdapterPizzas(getContext(),array_descr,array_names, array_sizes,array_price,array_photo,array_check,array_radio,array_check_second);
                            recyclerView.setAdapter(mAdapter);
=======
                            array_check.add(false);
                            array_radio.add(false);
                            array_check_second.add(false);
                            array_names.add(name);
                            CustomListAdapter adapter = new CustomListAdapter(getContext(),array_descr,array_names, array_sizes,array_price,array_photo,array_check,array_radio,array_check_second);
                            listView.setAdapter(adapter);
>>>>>>> eaed8754ab8c26a00865546921fe17c2d06cb1f5
                            progressDialog.dismiss();
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getActivity().getApplicationContext(), "Помилка !", Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        ref.addListenerForSingleValueEvent(eventListener);
    }



}
