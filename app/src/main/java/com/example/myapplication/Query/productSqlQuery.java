package com.example.myapplication.Query;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.Connector.connector;
import com.example.myapplication.model.Product;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class productSqlQuery {




   public int txtInventoryProductCount(Context context) {

     int total= 0;

        try {
            Connection con = new connector(context).getConnection();
            String sql = "select count (*) as ProductsCount from tblProduct";

            PreparedStatement ps = con.prepareStatement(sql);


            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getInt("ProductsCount") ;

            }

            rs.close();
            ps.close();
            con.close();


        } catch (Exception e) {
            Log.e("SQL", "Error getting product count", e);

        }
        return total;
    }


public int upperCountProduct( Context context){
        int total = 0;
        try{
            Connection con = new connector(context).getConnection();
            String sql = "select count (*) as ProductsCount from tblProduct";

            PreparedStatement ps = con.prepareStatement(sql);


            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                total = rs.getInt("ProductsCount");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return total ;
}

    public int UtangCount (Context context){
       int total = 0;
       try{

           Connection con = new connector(context).getConnection();

           String sql = "select count(*)as UtangCount from tblUtang";

           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
            if(rs.next()){
                total = rs.getInt("UtangCount");

           }
            rs.close();
            ps.close();
            con.close();

       } catch (Exception e) {
           throw new RuntimeException(e);
       }return total ;
    }
}
