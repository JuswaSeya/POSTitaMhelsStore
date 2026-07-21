package com.example.myapplication.Connector;

import android.content.Context;

import com.example.myapplication.Connector.database;

import java.sql.Connection;


public class connector {
    private Context context;
    private Connection con;


    public connector(Context context) {
        this.context = context;
    }

    public Connection getConnection() {
        database db = new database();
        database config = new database().DB1();
        con = db.connectionClass(config.server, config.password, config.database, config.ip);
        return con;


    }
}
