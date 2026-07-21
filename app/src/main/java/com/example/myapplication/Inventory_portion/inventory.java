package com.example.myapplication.Inventory_portion;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductAdapter;

import java.util.ArrayList;

public class inventory extends AppCompatActivity {

    RecyclerView recyclerInventory;



    ProductAdapter adapter;

    ArrayList<com.example.myapplication.model.Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_inventory);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.inventoryLayout),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        // ---------------------------------
        // CONNECT RECYCLERVIEW
        // ---------------------------------

        recyclerInventory =
                findViewById(R.id.recyclerInventory);


        // ---------------------------------
        // CREATE PRODUCT LIST
        // ---------------------------------

        productList = new ArrayList<>();


        // ---------------------------------
        // ADD SAMPLE PRODUCTS
        // ---------------------------------

        productList.add(new com.example.myapplication.model.Product(
                1,
                "Bonna 350g 6-12M",
                "4800153151248",
                "Milk",
                291.00,
                10,
                "July 21, 2026"
        ));

        productList.add(new com.example.myapplication.model.Product(
                2,
                "Bear Brand 33g",
                "4800012345678",
                "Milk",
                15.00,
                20,
                "July 21, 2026"
        ));


        // ---------------------------------
        // SET RECYCLERVIEW LAYOUT
        // ---------------------------------

        recyclerInventory.setLayoutManager(
                new LinearLayoutManager(this)
        );


        // ---------------------------------
        // CREATE ADAPTER
        // ---------------------------------

        adapter = new ProductAdapter(
                this,
                productList
        );


        // ---------------------------------
        // CONNECT ADAPTER
        // ---------------------------------

        recyclerInventory.setAdapter(adapter);
    }
}