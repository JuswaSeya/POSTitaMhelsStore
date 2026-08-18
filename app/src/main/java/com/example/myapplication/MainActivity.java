package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
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
    private TextView txtUtangUpperCount;
    private TextView txtDownUtangCount;
    private ProgressDialog progressDialog;
    private LinearLayout addutangNameLayout;

    ExecutorService executor = Executors.newSingleThreadExecutor();

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

    private void setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.dashboardID),
                (v, insets) -> {

                    Insets systemBars = insets.getInsets(
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
        txtUtangUpperCount = findViewById(R.id.txtUtangUpperCount);
        txtDownUtangCount=findViewById(R.id.txtDownUtangCount);

        addutangNameLayout = findViewById(R.id.addutangNameLayout);
    }

    private void setupClickListeners() {

        progressDialog = new ProgressDialog(MainActivity.this);

        salesLayout.setOnClickListener(v -> {

            executor.execute(() -> {
                runOnUiThread(() -> {

                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setTitle("Loading Sales");
                    progressDialog.setMessage("Please wait...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    finish();
                    startActivity(
                            new Intent(MainActivity.this, sales.class) );
                });



            });
        });


        addutangNameLayout.setOnClickListener(v -> {
        executor.execute(() -> {
            runOnUiThread(() -> {

//                progressDialog.setTitle("loading add Utang ");
//                progressDialog.setMessage("please wait");
//                progressDialog.setCancelable(false);
//                progressDialog.show();
//                finish();
                Toast.makeText(this,"add ka ng utang person",Toast.LENGTH_SHORT).show();
                startActivity( new Intent(MainActivity.this, add_utang_name.class));

            });
        });
        });






        inventoryLayout.setOnClickListener(v -> {

            progressDialog.setTitle("Loading Inventory");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

                runOnUiThread(() -> {



                    Toast.makeText(this,"Mga Produkto ni Tita Mhels", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, inventory.class));

                });


            });


        utangClickableDashboard.setOnClickListener(v -> {
            executor.execute(() -> {
                runOnUiThread(() -> {

                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setTitle("Loading");
                    progressDialog.setMessage("Please wait...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    finish();
                    startActivity(new Intent(MainActivity.this, utang.class));
                });
            });
        });
    }

    private void updateDashboardCounts() {

        productSqlQuery query = new productSqlQuery();
        // Update Product counts
        int upperProductCount = query.upperCountProduct(MainActivity.this);
        int inventoryProductCount = query.txtInventoryProductCount(MainActivity.this);
        int utangUpperCount=query.UtangUpperCount(MainActivity.this);
        int utangDownCount=query.UtangUpperCount(MainActivity.this);


        txtUpperProductCount.setText(String.valueOf(upperProductCount));
        txtUtangUpperCount.setText(String.valueOf(utangUpperCount));
        txtDownUtangCount.setText(String.valueOf(utangDownCount + " Utangs"));

        txtInventoryProductCount.setText( inventoryProductCount + " Products");

    }


}