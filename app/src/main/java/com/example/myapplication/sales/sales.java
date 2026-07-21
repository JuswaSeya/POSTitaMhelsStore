package com.example.myapplication.sales;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductAdapter;


import java.util.ArrayList;

public class sales extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;

    ImageButton btnBackSales;
    ArrayList<com.example.myapplication.model.Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        recyclerView = findViewById(R.id.recyclerProducts);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();



        adapter = new ProductAdapter(this, productList);

        recyclerView.setAdapter(adapter);

        btnBackSales = findViewById(R.id.btnBackSales);

        btnBackSales.setOnClickListener(v -> {
            startActivity(new Intent(sales.this,MainActivity.class));
        });



    }

}