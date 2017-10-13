package com.example.andrii.myapplication;
import android.content.Context;
import android.support.v4.app.Fragment;
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
    private final ArrayList arrayList_photo;
    private final ArrayList arrayonline;
    private final ArrayList arrayList1;


    public CustomListAdapter(Context context, ArrayList arrayList, ArrayList arrayList_photo, ArrayList arrayonline, ArrayList arrayList1) {
        super(context, R.layout.mylist, arrayList);
        this.context =  context;
        this.arrayList = arrayList;
        this.arrayList_photo = arrayList_photo;
        this.arrayonline = arrayonline;
        this.arrayList1 = arrayList1;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.mylist, null,true);
        details_fragment = new Details_fragment();
        Button but_details = (Button) rowView.findViewById(R.id.button8);
        but_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = ((ListTasks)context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, details_fragment).commit();
            }
        });
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        Picasso.with(context)
                .load(String.valueOf(arrayList1.get(position)))
                .into(imageView);
        txtTitle.setText((String) arrayList.get(position));
        return rowView;

    }


}
