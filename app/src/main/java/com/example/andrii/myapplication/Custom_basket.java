package com.example.andrii.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Custom_basket extends ArrayAdapter{
    private Context context;
    private ArrayList<String> arrayName;
    private ArrayList<String> arrayPhoto;
    private ArrayList<String> arraySize;
    private ArrayList<String> arrayPrice;
    private Custom_basket adapter = this;


    public Custom_basket(Context context, ArrayList arrayName, ArrayList arrayPhoto, ArrayList arraySize, ArrayList arrayPrice) {
        super(context, R.layout.mylist_basket,arrayName);
        this.context =  context;
        this.arrayName = arrayName;
        this.arrayPhoto = arrayPhoto;
        this.arrayPrice = arrayPrice;
        this.arraySize = arraySize;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.mylist_basket, null,true);
        TextView name = (TextView) rowView.findViewById(R.id.item);
        TextView size = (TextView) rowView.findViewById(R.id.size);
        TextView price = (TextView) rowView.findViewById(R.id.price);
        TextView count = (TextView) rowView.findViewById(R.id.count);
        int counts = 1;
        count.setText(counts + "шт.");
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        Picasso.with(context)
                .load(String.valueOf(arrayPhoto.get(position)))
                .into(imageView);
        name.setText(arrayName.get(position));
        size.setText(arraySize.get(position));
        price.setText(arrayPrice.get(position));
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.remove(adapter.getItem(position));
                adapter.notifyDataSetChanged();
            }
        });



        return rowView;
    }
}
