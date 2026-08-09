package com.example.myapplication.Inventory_portion;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Query.productSqlQuery;
import com.example.myapplication.R;
import com.example.myapplication.Utang_Package.utang;
import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.adapter.UtangsAdapter;
import com.example.myapplication.model.Product_model;

import java.util.ArrayList;

public class inventory extends AppCompatActivity {

    RecyclerView recyclerInventory;
    Button btnAddProduct;

    ProductAdapter adapter;

//    ArrayList<Product_model> productList;

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

        recyclerInventory = findViewById(R.id.recyclerInventory);
        recyclerInventory.setLayoutManager(new LinearLayoutManager(this));

        recyclerInventory = findViewById(R.id.recyclerInventory);

        recyclerInventory.setLayoutManager(
                new LinearLayoutManager(this)
        );



// Attach adapter immediately




        recyclerInventory.setAdapter(adapter);


// Get SQL data
        productSqlQuery sql =
                new productSqlQuery();

        new Thread(() -> {

            ArrayList<Product_model> productlist =sql.getProductDetails(inventory.this);

            runOnUiThread(() -> {

                adapter = new ProductAdapter(inventory.this, productlist);
                recyclerInventory.setAdapter(adapter);

            });

        }).start();

        EditText etSearch;
        etSearch=findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null) {
                    adapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnAddProduct = findViewById(R.id.btnAddProduct);

        btnAddProduct.setOnClickListener(v ->
                startActivity(new Intent(inventory.this, add_product.class)));


    }


}
