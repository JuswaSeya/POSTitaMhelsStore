package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Query.productSqlQuery;
import com.example.myapplication.Utang_Package.utang_details;
import com.example.myapplication.model.Utang_model;

public class edit_Utang_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_utang_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.editUtangLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int id = getIntent().getIntExtra("id", 0);
        Utang_model model =  new productSqlQuery().CurrentName(this,id);
        ImageView btnBack;

        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v->{
            startActivity(new Intent(edit_Utang_details.this, utang_details.class));
        });

        EditText edtFullName;
        edtFullName=findViewById(R.id.edtFullName);
        edtFullName.setText(String.valueOf(id));



        if(model!= null){

            edtFullName.setText(model.getFullname());
        }

//        TextView CurrentFullName,CurrentContactNumber;
//        CurrentFullName=findViewById(R.id.CurrentFullName);
//        CurrentContactNumber=findViewById(R.id.CurrentContactNumber);
//
//
//        CurrentFullName.setText(String.valueOf(id));

    }
}