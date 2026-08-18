package com.example.myapplication.Utang_Package;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.MainActivity;
import com.example.myapplication.Query.productSqlQuery;
import com.example.myapplication.R;
import com.example.myapplication.adapter.UtangsAdapter;
import com.example.myapplication.model.Utang_model;

import java.util.ArrayList;





public class utang extends AppCompatActivity {

    private UtangsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_utang);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.customerUtangLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;

        });



        Button btnAddCustomer;
        btnAddCustomer = findViewById(R.id.btnAddCustomer);
        btnAddCustomer.setOnClickListener(v->{
            startActivity(new Intent(utang.this, add_new_utang.class));
        });


        productSqlQuery sql;


        sql = new productSqlQuery();
        RecyclerView recyclerView;

        recyclerView = findViewById(R.id.recyclerUtang); // to call what to recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //this is for the context of the sql

        ImageView btnBack;
        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v->{
            startActivity(new Intent(this, MainActivity.class));

        });




        new Thread(() -> {

            ArrayList<Utang_model> list = sql.getUtangDetails (utang.this);
            Log.d("utang", "list " + list.size());
            runOnUiThread(() -> {
                adapter = new UtangsAdapter(utang.this, list, new UtangsAdapter.OnItemActionListener(){


                    @Override
                    public void onAdd(Utang_model model, int position) {
                        Intent intent =  new Intent(utang.this,utang_details.class);

                        intent.putExtra("id" , model.getId());
                        intent.putExtra("fullname", model.getFullname());
                        intent.putExtra("address",model.getAddress());
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            });

        }).start();






    }

//    RecyclerView recyclerView =findViewById(item_customer_utang)
}