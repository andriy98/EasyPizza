package com.example.andrii.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import at.markushi.ui.CircleButton;

public class RecyclerAdapterPizzas extends RecyclerView.Adapter<RecyclerAdapterPizzas.ViewHolder>{
    private Context context;
    private final ArrayList<String> arrauSize;
    private final ArrayList<String> arrayPrice;
    private final ArrayList arrayPhoto;
    private final ArrayList arrayDesc;
    private final ArrayList<Boolean> array_radio;
    private final ArrayList<Boolean> array_check;
    private final ArrayList<Boolean> array_check_second;
    private final ArrayList arrayList;
    private MenuItem item;
    private String [] strings;
    private static DataBaseHelper myDB;

    public RecyclerAdapterPizzas(Context context, ArrayList arrayDesc, ArrayList arrayList, ArrayList arrauSize, ArrayList arrayPrice, ArrayList arrayPhoto, ArrayList<Boolean> array_check, ArrayList<Boolean> array_radio, ArrayList<Boolean> array_check_second) {
        this.context =  context;
        this.arrayList = arrayList;
        this.arrauSize = arrauSize;
        this.arrayPrice = arrayPrice;
        this.arrayPhoto = arrayPhoto;
        this.arrayDesc = arrayDesc;
        this.array_check = array_check;
        this.array_check_second = array_check_second;
        this.array_radio = array_radio;
    }

    @Override
    public RecyclerAdapterPizzas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mylist, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterPizzas.ViewHolder holder, final int position) {
        myDB = new DataBaseHelper(context);
        item = MainActivity_drawer.item;
        if (array_radio.get(position)==false){
            holder.radio1.setChecked(true);
        }else if (array_radio.get(position)==true){
            holder.radio2.setChecked(true);
        }else {
            holder.radio1.setChecked(true);
        }

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor data = myDB.getAllData();
                if (array_radio.get(position) == false) {
                    if (array_check.get(position) == false) {
                        strings = arrauSize.get(position).split("-");
                        holder.AddData(String.valueOf(arrayList.get(position)), String.valueOf(arrayPhoto.get(position)),
                                strings[0].trim(),strings[1]);
                        ActionItemBadge.update(item, data.getCount());
                        Toast.makeText(context, "Піцу успішно додано в корзину !", Toast.LENGTH_LONG).show();
                        array_check.set(position, true);
                    }else {
                        Toast.makeText(context, "Піца уже в корзині !", Toast.LENGTH_SHORT).show();
                    }
                }
                if (array_radio.get(position) == true) {
                    if (array_check_second.get(position) == false) {
                        strings = arrayPrice.get(position).split("-");
                        holder.AddData(String.valueOf(arrayList.get(position)), String.valueOf(arrayPhoto.get(position)),
                                strings[0].trim(), strings[1]);
                        ActionItemBadge.update(item, data.getCount());
                        Toast.makeText(context, "Піцу успішно додано в корзину !", Toast.LENGTH_LONG).show();
                        array_check_second.set(position, true);
                    }
                    else {
                        Toast.makeText(context, "Піца уже в корзині!", Toast.LENGTH_SHORT).show();
                    }
                }
            }});



        holder.txtTitle.setText((String)arrayList.get(position));
        holder.txtDesc.setText((String)arrayDesc.get(position));
        Picasso.with(context)
                .load(String.valueOf(arrayPhoto.get(position)))
                .into(holder.imageView);
        holder.radio1.setText(arrauSize.get(position));
        holder.radio2.setText(arrayPrice.get(position));
        holder.radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                array_radio.set(position,true);
            }
        });
        holder.radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.radio2.setChecked(false);
                array_radio.set(position,false);
            }
        });
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RadioButton radio1, radio2;
        public CircleButton add;
        public MenuItem item;
        public TextView txtTitle;
        public TextView txtDesc;
        public ImageView imageView;


        public ViewHolder(View v) {
            super(v);
            txtTitle = (TextView) v.findViewById(R.id.item);
            txtDesc = (TextView) v.findViewById(R.id.desc);
            item = MainActivity_drawer.item;
            radio1 = (RadioButton) v.findViewById(R.id.radio1);
            radio2 = (RadioButton) v.findViewById(R.id.radio2);
            add = (CircleButton) v.findViewById(R.id.add);

            imageView = (ImageView) v.findViewById(R.id.imageView);
        }
        public void AddData(String newEntry_1, String newEntry_2, String newEntry_3 , String newEntry_4 ) {

            boolean insertData = myDB.insertData(newEntry_1,newEntry_2,newEntry_3,newEntry_4);

            if(insertData==true){

            }else{

            }
        }
    }
}