package com.example.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Product_model;

import java.sql.Array;
import java.util.ArrayList;

public class ProductAdapter
        extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product_model> productList;

    private ArrayList<Product_model> originalList;

    public ProductAdapter(
            Context context,
            ArrayList<Product_model> productList) {

        this.context = context;
        this.productList = productList;
        this.originalList=new ArrayList<>(productList);
        Log.d("productList", "list " + productList.size());
    }


    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        // Inflate the individual product card
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.activity_item_inventory,
                        parent,
                        false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        Product_model p = productList.get(position);

        holder.txtProductName.setText(p.getProductName());

        holder.productID.setText("Product ID"+p.getProductID());

        holder.txtBarcode.setText( "Barcode: " + p.getBarcode());

        holder.txtCategory.setText("Category: " + p.getCategory());

        holder.txtPrice.setText( String.format("Price: ₱%.2f",p.getPrice()));

        holder.txtStock.setText(
                "Stock: " + p.getStock());

        // Edit button
        holder.btnEdit.setOnClickListener(v -> {



        });

        // Delete button
        holder.btnDelete.setOnClickListener(v -> {

            int currentPosition =
                    holder.getAdapterPosition();

            if (currentPosition !=
                    RecyclerView.NO_POSITION) {

                productList.remove(currentPosition);

                notifyItemRemoved(currentPosition);
            }

        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productID;

        TextView txtProductName;
        TextView txtBarcode;
        TextView txtCategory;
        TextView txtPrice;
        TextView txtStock;

        Button btnEdit;
        Button btnDelete;

        public ViewHolder(
                @NonNull View itemView) {

            super(itemView);

            productID=itemView.findViewById(R.id.productID);



            txtProductName =
                    itemView.findViewById(
                            R.id.txtProductName);

            txtBarcode =
                    itemView.findViewById(
                            R.id.txtBarcode);

            txtCategory =
                    itemView.findViewById(
                            R.id.txtCategory);

            txtPrice =
                    itemView.findViewById(
                            R.id.txtPrice);

            txtStock =
                    itemView.findViewById(
                            R.id.txtStock);

            btnEdit =
                    itemView.findViewById(
                            R.id.btnEdit);

            btnDelete =
                    itemView.findViewById(
                            R.id.btnDelete);
        }
    }
    public void filter(String text) {

        productList.clear();

        if (text == null || text.trim().isEmpty()) {

            productList.addAll(originalList);

        } else {

            String searchText = text.toLowerCase().trim();

            for (Product_model product : originalList) {

                if (product.getProductName()
                        .toLowerCase()
                        .contains(searchText)) {

                    productList.add(product);
                }
            }
        }

        notifyDataSetChanged();
    }
}