package com.example.andrii.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main_fragment extends Fragment {
    private DatabaseReference myRef;
    private ListView listView;
    private ArrayList<String> array_descr = new ArrayList<>();
    private ArrayList<String> array_photo = new ArrayList<>();
    private ArrayList<String> array_price = new ArrayList<>();
    private ArrayList<Boolean> array_check = new ArrayList<>();
    private Map<String, String> data;
    private ArrayList<String> array_sizes = new ArrayList<>();
    private ArrayList<String> array_names = new ArrayList<>();
    private static final int LAYOUT = R.layout.list_tasks;
    private View view;
    private ProgressDialog progressDialog;

    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle("Піца");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        listView = (ListView) view.findViewById(R.id.discr_for_task);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        myRef = FirebaseDatabase.getInstance().getReference();

        getPizzas();
        progressDialog = ProgressDialog.show(getContext(),"Завантаження","Зачекайте, будь ласка...",false,false);
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
                            array_check.add(false);
                            array_names.add(name);
                            CustomListAdapter adapter = new CustomListAdapter(getContext(),array_descr,array_names, array_sizes,array_price,array_photo,array_check);
                            listView.setAdapter(adapter);
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
