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

import at.markushi.ui.CircleButton;


public class RecyclerAdapterBasket extends RecyclerView.Adapter<RecyclerAdapterBasket.ViewHolder> {
    private Context context;
    private ArrayList<String> arrayName;
    private ArrayList<String> arrayPhoto;
    private ArrayList<String> arraySize;
    private ArrayList<String> arrayPrice;
    private ArrayList<String> arrayPizzeria;
    private DataBaseHelper myDB;
    private DBHelp_price dbHelpPrice;
    private static MenuItem item;



    public RecyclerAdapterBasket(Context context, ArrayList arrayName, ArrayList arrayPhoto, ArrayList arraySize, ArrayList arrayPrice, ArrayList arrayPizzeria) {
        this.context =  context;
        this.arrayName = arrayName;
        this.arrayPhoto = arrayPhoto;
        this.arrayPrice = arrayPrice;
        this.arraySize = arraySize;
        this.arrayPizzeria = arrayPizzeria;
    }





    @Override
    public RecyclerAdapterBasket.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mylist_basket, parent, false);
        RecyclerAdapterBasket.ViewHolder vh = new RecyclerAdapterBasket.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterBasket.ViewHolder holder, final int position) {
        myDB = new DataBaseHelper(context);
        dbHelpPrice = new DBHelp_price(context);
        final ArrayList<Integer> array_int = new ArrayList<>();
        final ArrayList<String> array_newprice = new ArrayList<>();
        final Cursor data_data = dbHelpPrice.getAllData();
        while (data_data.moveToNext()){
            array_int.add(data_data.getInt(5));
            array_newprice.add(data_data.getString(4));
        }
        holder.counts = array_int.get(position);
        final Cursor cursor = myDB.getAllData();
        //dbHelpPrice.insertData(arrayName.get(position),arrayPhoto.get(position),arraySize.get(position),arrayPrice.get(position),holder.counts);
        holder.count.setText(holder.counts + " шт.");
        Picasso.with(context)
                .load(String.valueOf(arrayPhoto.get(position)))
                .into(holder.imageView);

        holder.name.setText(arrayName.get(position));
        holder.size.setText(arraySize.get(position));
        holder.pizzeria.setText(arrayPizzeria.get(position));
        holder.price.setText(array_newprice.get(position));
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] subStr,old;
                String str,str_old;
                str = (String) holder.price.getText();
                subStr = str.split(" ");
                str_old = arrayPrice.get(position);
                old = str_old.split(" ");
                int price;
                int first_price = Integer.parseInt(old[1]);
                price =  Integer.parseInt(subStr[1]);
                price += first_price;
                holder.counts++;
                holder.count.setText(holder.counts + " шт.");
                holder.price.setText(" "+price+" грн.");
                boolean what = dbHelpPrice.updateData(arrayName.get(position),arrayPhoto.get(position),arraySize.get(position),holder.price.getText().toString(),holder.counts,arrayPizzeria.get(position));
            }
        });
        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.counts>1) {
                    String[] subStr, old;
                    String str, str_old;
                    str = (String) holder.price.getText();
                    subStr = str.split(" ");
                    str_old = arrayPrice.get(position);
                    old = str_old.split(" ");
                    int price;
                    int first_price = Integer.parseInt(old[1]);
                    price = Integer.parseInt(subStr[1]);
                    price -= first_price;
                    holder.counts--;
                    holder.count.setText(holder.counts + " шт.");
                    holder.price.setText(" " + price + " грн.");
                    boolean what = dbHelpPrice.updateData(arrayName.get(position),arrayPhoto.get(position),arraySize.get(position),holder.price.getText().toString(),holder.counts,arrayPizzeria.get(position));
                }
            }
        });
        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(arrayName.get(position),arrayPrice.get(position));
                dbHelpPrice.deleteData(arrayName.get(position),array_newprice.get(position),arrayPizzeria.get(position));
                arraySize.remove(position);
                arrayPrice.remove(position);
                arrayPhoto.remove(position);
                arrayName.remove(position);
                arrayPizzeria.remove(position);
                ActionItemBadge.update(item,cursor.getCount());
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,arrayName.size());
            }
        });
        dbHelpPrice.close();
    }

    @Override
    public int getItemCount() {
        return arrayName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, size, price, count, pizzeria;
        public ImageView imageView, image_delete;
        public CircleButton increase,decrease;
        public int counts ;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.item);
            pizzeria = (TextView) v.findViewById(R.id.pizzeria);
            item = MainActivity_drawer.item;
            price = (TextView) v.findViewById(R.id.price);
            size = (TextView) v.findViewById(R.id.size);
            count = (TextView) v.findViewById(R.id.count);
            imageView = (ImageView) v.findViewById(R.id.icon);
            image_delete = (ImageView) v.findViewById(R.id.delete);
            increase  = (CircleButton) v.findViewById(R.id.inc);
            decrease  = (CircleButton) v.findViewById(R.id.dec);
        }
    }
    public void deleteData(String name, String price){
        boolean removeData = myDB.deleteData(name,price);
        if (removeData==false){

        }else {

        }
    }
}
