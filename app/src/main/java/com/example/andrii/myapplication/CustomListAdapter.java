package com.example.andrii.myapplication;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import at.markushi.ui.CircleButton;

import static android.R.attr.data;

public class CustomListAdapter extends ArrayAdapter {
    private Context context;
    private Details_fragment details_fragment;
    private Order_fragment order_fragment;
    private final ArrayList arrayList;
    private final ArrayList arrauSize;
    private final ArrayList arrayPrice;
    private final ArrayList arrayPhoto;
    private final ArrayList arrayDesc;
    private  FragmentTransaction fragmentTransaction;
    private Bundle bundle;
    private RadioButton radio1, radio2;
    private CircleButton add, delete;
    private MenuItem item;
    private int badgeCount = 0;




    public CustomListAdapter(Context context, ArrayList arrayDesc, ArrayList arrayList, ArrayList arrauSize, ArrayList arrayPrice, ArrayList arrayPhoto) {
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
        order_fragment = new Order_fragment();
       // Button but_details = (Button) rowView.findViewById(R.id.button8);
        RadioGroup radioGroup = (RadioGroup) rowView.findViewById(R.id.radioGroup);
            item = MainActivity.item;
        radio1 = (RadioButton) rowView.findViewById(R.id.radio1);
        radio2 = (RadioButton) rowView.findViewById(R.id.radio2);
        radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (radio1.isChecked()==true){
                    radio2.setChecked(false);
                }else {
                    radio2.setChecked(true);
                }
            }
        });









        // Button but_order = (Button) rowView.findViewById(R.id.button7);
      /*  but_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();
                bundle = new Bundle();
                bundle.putString("Desc", String.valueOf(arrayDesc.get(position)));
                bundle.putString("Size", String.valueOf(arrauSize.get(position)));
                bundle.putString("Photo", String.valueOf(arrayPhoto.get(position)));
                bundle.putString("Name", String.valueOf(arrayList.get(position)));
                bundle.putString("Price", String.valueOf(arrayPrice.get(position)));
                order_fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container, order_fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        but_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();
                bundle = new Bundle();
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
        Розмітка:
        /*  <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:weightSum="1">

        <Button
            android:id="@+id/button7"
            style="@style/Widget.AppCompat.Button.Colored"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:stateListAnimator="@animator/animator"
            android:text="Замовити"


            ></Button>

        <Button
            android:id="@+id/button8"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentEnd="true"
            android:layout_alignParentTop="true"
            style="@style/Widget.AppCompat.Button.Colored"
            android:stateListAnimator="@animator/animator"
            android:text="Детальніше" />

    </RelativeLayout>*/
        add = (CircleButton) rowView.findViewById(R.id.add);
        delete = (CircleButton) rowView.findViewById(R.id.delete);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    badgeCount++;
                    ActionItemBadge.update(item,badgeCount);
                    Toast.makeText(getContext(), "Піцу успішно додано в корзину !", Toast.LENGTH_LONG).show();
                }
            });
        radio1.setText((String) arrauSize.get(position));
        radio2.setText((String) arrayPrice.get(position));
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        TextView txtDesc = (TextView) rowView.findViewById(R.id.desc);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        Picasso.with(context)
                .load(String.valueOf(arrayPhoto.get(position)))
                .into(imageView);
        txtDesc.setText((String) arrayDesc.get(position));
        txtTitle.setText((String) arrayList.get(position));
        return rowView;

    }


}
