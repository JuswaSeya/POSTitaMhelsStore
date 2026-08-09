package com.example.myapplication.Inventory_portion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.model.Product_model;

import java.util.ArrayList;

public class item_inventory extends AppCompatActivity {

    RecyclerView recyclerInventory;

    ProductAdapter adapter;

    ArrayList<Product_model> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inventory);

        // Connect RecyclerView
        recyclerInventory =
                findViewById(R.id.recyclerInventory);

        // Create ArrayList
        productList = new ArrayList<>();

        // Add sample products
        productList.add(new Product_model(
                1,
                "Bonna 350g 6-12M",
                "4800153151248",
                "Milk",
                291.00,
                10,
                "July 21, 2026"
        ));

        productList.add(new Product_model(
                2,
                "Bear Brand 33g",
                "4800012345678",
                "Milk",
                15.00,
                20,
                "July 21, 2026"
        ));

        // Set RecyclerView LayoutManager
        recyclerInventory.setLayoutManager(
                new LinearLayoutManager(this)
        );

        // Connect Adapter
        adapter = new ProductAdapter(
                this,
                productList
        );

        // Set Adapter
        recyclerInventory.setAdapter(adapter);
    }
}