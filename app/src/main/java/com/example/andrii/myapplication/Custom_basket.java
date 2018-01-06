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
    private ArrayList<String> arrayName = new ArrayList<>();
    private ArrayList<String> arrayPhoto = new ArrayList<>();
    private ArrayList<String> arraySize = new ArrayList<>();
    private ArrayList<String> arrayPrice = new ArrayList<>();


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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.mylist_basket, null,true);
        TextView name = (TextView) rowView.findViewById(R.id.item);
        TextView size = (TextView) rowView.findViewById(R.id.size);
        TextView price = (TextView) rowView.findViewById(R.id.price);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        Picasso.with(context)
                .load(String.valueOf(arrayPhoto.get(position)))
                .into(imageView);
        name.setText(arrayName.get(position));
        size.setText(arraySize.get(position));
        price.setText(arrayPrice.get(position));



        return rowView;
    }
}
