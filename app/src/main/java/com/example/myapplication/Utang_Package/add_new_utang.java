package com.example.myapplication.Utang_Package;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Query.productSqlQuery;
import com.example.myapplication.R;
import com.example.myapplication.model.add_new_utang_model;


public class add_new_utang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_utang);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addnewutanglayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnBack;
        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v->{
            startActivity(new Intent(add_new_utang.this, utang.class));
        });



        EditText edtPersonName;
        EditText edtContactNumber;
        EditText edtUtangDescription;
        EditText edtAmount;
        EditText edtDateBorrowed;
        EditText edtDueDate;
        EditText edtNotes;
        Button btnSaveUtang;

        edtPersonName = findViewById(R.id.edtPersonName);

        edtContactNumber = findViewById(R.id.edtContactNumber);

        edtUtangDescription = findViewById(R.id.edtUtangDescription);

        edtAmount = findViewById(R.id.edtAmount);


        edtNotes =
                findViewById(R.id.edtNotes);

        btnSaveUtang =  findViewById(R.id.btnSaveUtang);

        btnBack =  findViewById(R.id.btnBack);
        btnBack.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v->{
            startActivity(new Intent(this, utang.class));
        });


        btnSaveUtang.setOnClickListener(v->{








        });








    }




}