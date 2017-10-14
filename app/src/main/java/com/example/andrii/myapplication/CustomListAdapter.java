package com.example.andrii.myapplication;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter {
    private Context context;
    private Details_fragment details_fragment;
    private final ArrayList arrayList;
    private final ArrayList arrauSize;
    private final ArrayList arrayPrice;
    private final ArrayList arrayPhoto;
    private final ArrayList arrayDesc;


    public CustomListAdapter(Context context, ArrayList arrayDesc , ArrayList arrayList, ArrayList arrauSize, ArrayList arrayPrice, ArrayList arrayPhoto) {
        super(context, R.layout.mylist, arrayList);
        this.context =  context;
        this.arrayList = arrayList;
        this.arrauSize = arrauSize;
        this.arrayPrice = arrayPrice;
        this.arrayPhoto = arrayPhoto;
        this.arrayDesc = arrayDesc;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.mylist, null,true);
        details_fragment = new Details_fragment();
        Button but_details = (Button) rowView.findViewById(R.id.button8);
        but_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = ((ListTasks)context).getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("Desc", String.valueOf(arrayDesc.get(position)));
                bundle.putString("Size", String.valueOf(arrauSize.get(position)));
                bundle.putString("Photo", String.valueOf(arrayPhoto.get(position)));
                bundle.putString("Name", String.valueOf(arrayList.get(position)));
                bundle.putString("Price", String.valueOf(arrayPrice.get(position)));
                details_fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container, details_fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        Picasso.with(context)
                .load(String.valueOf(arrayPhoto.get(position)))
                .into(imageView);
        txtTitle.setText((String) arrayList.get(position));
        return rowView;

    }


}
