package com.example.myapplication.Utang_Package;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class utang_details extends AppCompatActivity {
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_utang_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.utangDetailsLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        progressDialog=new ProgressDialog(utang_details.this);
        ExecutorService execute = Executors.newSingleThreadExecutor();

        Button btnAddUtang;
        ImageButton btnBack;

        btnBack=findViewById(R.id.btnBack);

        btnBack.setOnClickListener(view->{
            execute.execute(()->{
                runOnUiThread(()->{
                    progressDialog.setTitle("loading..");
                    progressDialog.setMessage("Please Wait");
                    progressDialog.setCancelable(false);
                    startActivity(new Intent(utang_details.this, utang.class));
                });
            });
        });


        btnAddUtang=findViewById(R.id.btnAddUtang);
        btnAddUtang.setOnClickListener(v->{
            execute.execute(()->{
                runOnUiThread(()->{
                    progressDialog.setTitle("loading..");
                    progressDialog.setMessage("Please Wait");
                    progressDialog.setCancelable(false);
                    startActivity(new Intent(utang_details.this, add_Utang_ofThePerson.class));
                });


            });

            });







        TextView txtCustomerName;
        txtCustomerName=findViewById(R.id.txtCustomerName);

//        String name = sql.getUtangDetails(utang_details.this);
//        txtCustomerName.setText(String.valueOf(name));


    }
}