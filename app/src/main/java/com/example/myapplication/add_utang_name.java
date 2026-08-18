package com.example.myapplication;

import android.app.ProgressDialog;
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

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class add_utang_name extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_utang_name);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nameUtangs), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        ExecutorService executor= Executors.newSingleThreadExecutor();





        Button btnAdd;
        btnAdd=findViewById(R.id.btnAdd);
        ImageButton imageBack;
        imageBack=findViewById(R.id.imageBack);




        btnAdd.setOnClickListener(view->{
            EditText addUtangFullnames, addUtangContactNumber,addUtangAddress;



            addUtangFullnames=findViewById(R.id.addUtangFullname);
            addUtangContactNumber=findViewById(R.id.addUtangContactNumber);
            addUtangAddress=findViewById(R.id.addUtangAddress);


            String fullname = addUtangFullnames.getText().toString();
            String ContactNumber = addUtangContactNumber.getText().toString();
            String UtangAdress = addUtangAddress.getText().toString();


             executor.execute(()->{

               boolean utangNameSql = new productSqlQuery().utangNameSql(this,fullname,ContactNumber,UtangAdress);
               runOnUiThread(()->{
               if(utangNameSql){
                   Toast.makeText(this, "Sucessfully Saved!", Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
               }
               });
           });

        });

        imageBack.setOnClickListener(v->{
//            executor.execute(()->{


            runOnUiThread(()->{
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("Loading...Please wait");
            dialog.show();

            });
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(add_utang_name.this,MainActivity.class));
                finish();
            });
//        });

    }
}