package com.example.andrii.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andrii.myapplication.data.Pizzaria;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Pizzerias_fragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private Context context;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mPizzariesDatabaseReference;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pizzerias, container, false);
        recyclerView = view.findViewById(R.id.pizzerias_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mPizzariesDatabaseReference = mFirebaseDatabase.getReference().child("Pizzaries");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle("Піцерії");
        progressDialog = ProgressDialog.show(getContext(), "Завантаження", "Зачекайте, будь ласка...", false, false);
        showPizzerias();
    }

    public void showPizzerias() {
        final List<Pizzaria> pizzariaList = new ArrayList<>();
        mPizzariesDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    Pizzaria pizzaria = shot.getValue(Pizzaria.class);
                    if (pizzaria != null) {
                        System.out.println(pizzaria.toString());
                        pizzariaList.add(pizzaria);
                    }
                }
                recyclerView.setAdapter(new PizzariesRVAdapter(pizzariaList));
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                log events somewhere !!!
//                show user message
                Toast.makeText(getContext(), "Failed while get data! Please try again!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private class PizzariesRVAdapter extends RecyclerView.Adapter<PizzariesRVAdapter.PizzariesViewHolder> {
        private List<Pizzaria> pizzariaList;

        PizzariesRVAdapter(List<Pizzaria> pizzariaList) {
            this.pizzariaList = pizzariaList;
        }

        @Override
        public PizzariesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new PizzariesViewHolder(inflater.inflate(R.layout.item_pizzerias_card_view, parent, false));
        }

        @Override
        public void onBindViewHolder(PizzariesViewHolder holder, int position) {
            Picasso.with(context)
                    .load(String.valueOf(pizzariaList.get(position).getLogo()))
                    .into(holder.pizzariaLogoImageView);

            holder.pizzariaTitleTextView.setText(pizzariaList.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return pizzariaList.size();
        }

        class PizzariesViewHolder extends RecyclerView.ViewHolder {
            TextView pizzariaTitleTextView;
            ImageView pizzariaLogoImageView;
            public PizzariesViewHolder(View itemView) {
                super(itemView);
                pizzariaTitleTextView= itemView.findViewById(R.id.item_pizzaria_title_text_view);
                pizzariaLogoImageView = itemView.findViewById(R.id.item_pizzaria_logo_image_view);
            }
        }
    }
}
