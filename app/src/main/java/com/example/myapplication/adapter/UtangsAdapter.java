package com.example.myapplication.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Utang_Package.item_customer_utang;
import com.example.myapplication.Utang_Package.utang;
import com.example.myapplication.Utang_Package.utang_details;
import com.example.myapplication.model.Utang_model;

import java.util.ArrayList;

public class UtangsAdapter extends RecyclerView.Adapter<UtangsAdapter.UtangViewHolder> {

    private Context context;
    private ArrayList<Utang_model> UtangList;

    public UtangsAdapter(
            Context context,
            ArrayList<Utang_model> UtangList) {

        this.context = context;
        this.UtangList = UtangList;
    }


    @Override
    public UtangViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_customer_utang,
                        parent,
                        false


                );

        return new UtangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull UtangViewHolder holder,
            int position) {



        Utang_model UM = UtangList.get(position);

        holder.txtCustomerName.setText(UM.getName());
        holder.txtContact.setText(UM.getContact());

        holder.txtBalance.setText(
                "Balance : ₱" + String.format("%.2f", UM.getBalance())



        );

        holder.btnDetails.setOnClickListener(v -> {

            Intent intent= new Intent(context, utang_details.class);
            intent.putExtra("Utang_details", UM.getName());
            context.startActivity(intent);
        });

    }


    @Override
    public int getItemCount() {

        return UtangList.size();
    }

    public static class UtangViewHolder extends RecyclerView.ViewHolder {

        TextView txtCustomerName;
        TextView txtContact;
        TextView txtBalance;

ImageView btnDetails;
        public UtangViewHolder(@NonNull View view) {
            super(view);

            txtCustomerName = view.findViewById(R.id.txtCustomerName);
            txtContact = view.findViewById(R.id.txtContact);
            txtBalance = view.findViewById(R.id.txtBalance);

            btnDetails=view.findViewById(R.id.btnDetails);



        }
    }
}