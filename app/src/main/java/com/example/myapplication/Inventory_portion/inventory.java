package com.example.myapplication.Inventory_portion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.Query.productSqlQuery;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.model.Product_model;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class inventory extends AppCompatActivity {

    private RecyclerView recyclerInventory;
    private Button btnAddProduct;
    private ImageView btnBack;
    private EditText etSearch;

    private ProductAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        EdgeToEdge.enable( this );
        setContentView( R.layout.activity_inventory );

// Window insets
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById( R.id.inventoryLayout ),
                ( v, insets ) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars( )
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

// Initialize views
        recyclerInventory = findViewById( R.id.recyclerInventoryForItem );
        btnAddProduct = findViewById( R.id.btnAddProduct );
        btnBack = findViewById( R.id.btnBack );
        etSearch = findViewById( R.id.etSearch );
        ProgressDialog dialog = new ProgressDialog( inventory.this );
        ExecutorService execute = Executors.newSingleThreadExecutor( );


// RecyclerView setup
        recyclerInventory.setLayoutManager( new LinearLayoutManager( this ) );

// Load product data
        loadProducts( );

// Search
        etSearch.addTextChangedListener( new TextWatcher( ) {

            @Override
            public void beforeTextChanged(
                    CharSequence s,
                    int start,
                    int count,
                    int after
            ) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s,
                    int start,
                    int before,
                    int count
            ) {
                if ( adapter != null ) {
                    adapter.filter( s.toString( ) );
                }
            }

            @Override
            public void afterTextChanged( Editable s ) {
            }
        } );

//            runOnUiThread( ( ) -> {




// Add product
                btnAddProduct.setOnClickListener( v -> {

                    dialog.setMessage("Loading...Please Wait");
                    dialog.setCancelable(false);
                    dialog.show();
                    startActivity( new Intent(inventory.this, add_product.class));
                });

// Back button
                btnBack.setOnClickListener( v -> {

                    dialog.setMessage("Loading...Please Wait");
                    dialog.setCancelable(false);
                    dialog.show();
                    startActivity( new Intent(inventory.this, MainActivity.class));

//            } );
                });


    }

    private void loadProducts( ) {

        productSqlQuery sql = new productSqlQuery( );

        new Thread( ( ) -> {

            ArrayList< Product_model > productList =
                    sql.getProductDetails( inventory.this );

            runOnUiThread( ( ) -> {

                adapter = new ProductAdapter(
                        inventory.this,
                        productList
                );

                recyclerInventory.setAdapter( adapter );
            } );

        } ).start( );
    }
}