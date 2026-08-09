package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Inventory_portion.inventory;
import com.example.myapplication.Query.productSqlQuery;
import com.example.myapplication.Utang_Package.utang;
import com.example.myapplication.sales.sales;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    // UI Components
    private CardView cardSales;
    private LinearLayout salesLayout;
    private LinearLayout inventoryLayout;
    private LinearLayout utangClickableDashboard;
    private TextView txtInventoryProductCount;
    private TextView txtUpperProductCount;
    private TextView txtUtangCount;
    private TextView txtCustomerUtangCount;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupWindowInsets();
        initializeViews();
        setupClickListeners();
        updateDashboardCounts();
    }

    ExecutorService executor = Executors.newSingleThreadExecutor();
    private void setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dashboardID), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeViews() {
        // Dashboard cards and layouts
        cardSales = findViewById(R.id.cardSales);
        inventoryLayout = findViewById(R.id.inventoryClickableLinearLayout);
        salesLayout = findViewById(R.id.sales_button);
        utangClickableDashboard = findViewById(R.id.utangclickableDashboard);

        // Text views for displaying counts
        txtInventoryProductCount = findViewById(R.id.txtInventoryProductCount);
        txtUpperProductCount = findViewById(R.id.txtUpperProductCount);
        txtUtangCount = findViewById(R.id.txtUtangCount);
        txtCustomerUtangCount = findViewById(R.id.txtCustomerUtangCount);
    }

    private void setupClickListeners() {

        salesLayout.setOnClickListener(v ->{
            executor.execute(()->{
                runOnUiThread(()->{
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setTitle("Loading");
                    progressDialog.setMessage("Please wait...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                });


                startActivity(new Intent(MainActivity.this, sales.class));
            });

            });



        inventoryLayout.setOnClickListener(v -> {

executor.execute(()->{
    runOnUiThread(()->{
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Toast.makeText(this, "Mga Produkto ni Tita Mhels", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, inventory.class));
    });

});



        });

        utangClickableDashboard.setOnClickListener(v ->{
                executor.execute(()-> {
                    runOnUiThread(() -> {
                        progressDialog = new ProgressDialog(MainActivity.this);
                        progressDialog.setTitle("Loading");
                        progressDialog.setMessage("Please wait...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        Toast.makeText(this, "Mga Produkto ni Tita Mhels", Toast.LENGTH_SHORT).show();

                    });
                    startActivity(new Intent(MainActivity.this, utang.class));
                });
        });
    };

    private void updateDashboardCounts() {
        productSqlQuery query = new productSqlQuery();

        // Update Utang counts
        int utangCount = query.UtangCount(MainActivity.this);
        txtCustomerUtangCount.setText(utangCount + " Customer");
        txtUtangCount.setText(String.valueOf(utangCount));

        // Update Product counts
        int upperProductCount = query.upperCountProduct(MainActivity.this);
        txtUpperProductCount.setText(String.valueOf(upperProductCount));

        int inventoryProductCount = query.txtInventoryProductCount(MainActivity.this);
        txtInventoryProductCount.setText(inventoryProductCount + " Products");
    }
}