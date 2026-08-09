package com.example.myapplication.Inventory_portion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Query.productSqlQuery;
import com.example.myapplication.R;
import com.example.myapplication.barcode;
import com.example.myapplication.model.add_product_details_models;

public class add_product extends AppCompatActivity {


    EditText etBarcode, etCategory, etPrice,etProductName,etStock,etDateExpiration;
Button btnSaveProduct,btnCancel,btnScanBarcode;
ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_product_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            startActivity(new Intent(this, inventory.class));
        });
        btnCancel =findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(v->{
            startActivity(new Intent(this,inventory.class));
        });
        btnSaveProduct=findViewById(R.id.btnSaveProduct);

        btnScanBarcode=findViewById(R.id.btnScanBarcode);
        btnScanBarcode.setOnClickListener(v->{
            startActivity(new Intent(this, barcode.class));
        });

        etDateExpiration=findViewById(R.id.etDateExpiration);
      etBarcode=findViewById(R.id.etBarcode);
        etCategory=findViewById(R.id.etCategory);
        etPrice=findViewById(R.id.etPrice);
        etProductName=findViewById(R.id.etProductName);
        etStock=findViewById(R.id.etStock);



        btnSaveProduct.setOnClickListener(v->{
            add_product_details_models model = new add_product_details_models();

            model.setProductname(etProductName.getText().toString());
            model.setBarcode (etBarcode.getText().toString());
            model.setCategory(etCategory.getText().toString());
            model.setPrice(Double.parseDouble(etPrice.getText().toString()));
            model.setStock(Integer.parseInt(etStock.getText().toString()));
            model.setEtDateExpiration(etDateExpiration.getText().toString());

            if (new productSqlQuery().addProduct(this,model)){
                Toast.makeText(this, "Successfully Save", Toast.LENGTH_SHORT).show();
                etCategory.setText("");
                etBarcode.setText("");
                etPrice.setText("");
                etStock.setText("");
                etProductName.setText("");
                etDateExpiration.setText("");
            }
            else{
                Toast.makeText(this, "Didnt save Error", Toast.LENGTH_SHORT).show();
            }



        });





    }


}