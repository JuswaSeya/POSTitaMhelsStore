package com.example.myapplication;

import static androidx.core.content.ContentProviderCompat.requireContext;

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
import com.example.myapplication.model.Product;
import com.example.myapplication.sales.sales;

public class MainActivity extends AppCompatActivity {
        //1
    CardView cardSales;
    LinearLayout salesLayout;
    LinearLayout inventoryLayout;
    LinearLayout  utangclickableDashboard;

    TextView txtInventoryProductCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dashboardID), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //2
        cardSales = findViewById(R.id.cardSales);
        inventoryLayout=findViewById(R.id.inventoryClickableLinearLayout);
        salesLayout=findViewById(R.id.sales_button);
        utangclickableDashboard=findViewById(R.id.utangclickableDashboard);

        salesLayout.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, sales.class))

        );

        inventoryLayout.setOnClickListener(v -> {
            Toast.makeText(this, "Mga Produkto ni Tita Mhels", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, inventory.class));


        });
        utangclickableDashboard.setOnClickListener(v->{
            startActivity(new Intent (MainActivity.this, utang.class));
        });


        //1.0
        TextView txtInventoryProductCount =findViewById(R.id.txtInventoryProductCount);
        TextView txtUpperProductCount = findViewById(R.id.txtUpperProductCount);
        TextView txtUtangCount = findViewById(R.id.txtUtangCount);
        TextView txtCustomerUtangCount = findViewById(R.id.txtCustomerUtangCount);

        //1.1
        productSqlQuery query = new productSqlQuery();



        int UtangCount = query.UtangCount(MainActivity.this);
        txtCustomerUtangCount.setText(String.valueOf(UtangCount)+" Customer");

        int UpperCustomerUtangCount = query.UtangCount(MainActivity.this);
        txtUtangCount.setText (String.valueOf(UpperCustomerUtangCount));

        int UpperProductCount= query.upperCountProduct(MainActivity.this);
        txtUpperProductCount.setText(String.valueOf(UpperProductCount));

int InventoryProductCount =query.txtInventoryProductCount(MainActivity.this);
        txtInventoryProductCount.setText(String.valueOf(InventoryProductCount)+" Products");







    }

}