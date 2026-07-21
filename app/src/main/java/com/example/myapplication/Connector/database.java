package com.example.myapplication.Connector;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;



    public class database {
        public String server, password, ip ,database;

        public database DB1(){

            database db = new database();
            db.server= "sa";
            db.password = "";
            db.ip="";
            db.database = "";
            return db;

        }

        @SuppressLint("NewApi")
        public Connection connectionClass(String user, String password, String database, String server) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection connection = null;
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                String connectionURL = "jdbc:jtds:sqlserver://" + server + ":1433/" + database +
                        ";user=" + user + ";password=" + password + ";";
                connection = DriverManager.getConnection(connectionURL);
            } catch (Exception e) {
                Log.e("SQL Connection Error", Log.getStackTraceString(e)); // full stack trace
            }
            return connection;
        }
    }


