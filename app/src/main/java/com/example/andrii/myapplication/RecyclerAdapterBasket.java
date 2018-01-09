package com.example.andrii.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecyclerAdapterBasket extends RecyclerView.Adapter<RecyclerAdapterBasket.ViewHolder> {
    private Context context;
    private ArrayList<String> arrayName;
    private ArrayList<String> arrayPhoto;
    private ArrayList<String> arraySize;
    private ArrayList<String> arrayPrice;
    private DataBaseHelper myDB;
    private static MenuItem item;



    public RecyclerAdapterBasket(Context context, ArrayList arrayName, ArrayList arrayPhoto, ArrayList arraySize, ArrayList arrayPrice) {
        this.context =  context;
        this.arrayName = arrayName;
        this.arrayPhoto = arrayPhoto;
        this.arrayPrice = arrayPrice;
        this.arraySize = arraySize;
    }





    @Override
    public RecyclerAdapterBasket.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mylist_basket, parent, false);
        RecyclerAdapterBasket.ViewHolder vh = new RecyclerAdapterBasket.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterBasket.ViewHolder holder, final int position) {
        myDB = new DataBaseHelper(context);
        final Cursor cursor = myDB.getAllData();
        int counts = 1;
        holder.count.setText(counts + "шт.");
        Picasso.with(context)
                .load(String.valueOf(arrayPhoto.get(position)))
                .into(holder.imageView);
        holder.name.setText(arrayName.get(position));
        holder.size.setText(arraySize.get(position));
        holder.price.setText(arrayPrice.get(position));
        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(arrayName.get(position),arrayPrice.get(position));
                arraySize.remove(position);
                arrayPrice.remove(position);
                arrayPhoto.remove(position);
                arrayName.remove(position);
                ActionItemBadge.update(item,cursor.getCount());
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,arrayName.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, size, price, count;
        public ImageView imageView, image_delete;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.item);
            item = MainActivity_drawer.item;
            price = (TextView) v.findViewById(R.id.price);
            size = (TextView) v.findViewById(R.id.size);
            count = (TextView) v.findViewById(R.id.count);
            imageView = (ImageView) v.findViewById(R.id.icon);
            image_delete = (ImageView) v.findViewById(R.id.delete);
        }


    }
    public void deleteData(String name, String price){
        boolean removeData = myDB.deleteData(name,price);
        if (removeData==false){

        }else {

        }
    }
}
