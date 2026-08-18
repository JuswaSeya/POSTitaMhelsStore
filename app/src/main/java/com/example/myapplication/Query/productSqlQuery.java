package com.example.myapplication.Query;

import android.content.Context;
import android.util.Log;


import com.example.myapplication.Connector.connector;
import com.example.myapplication.model.Product_model;
import com.example.myapplication.model.Utang_model;
import com.example.myapplication.model.add_new_utang_model;
import com.example.myapplication.model.add_product_details_models;


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
            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return total ;
}

//    public int UtangCount (Context context){
//       int total = 0;
//       try{
//
//           Connection con = new connector(context).getConnection();
//
//           String sql = "select count(*)as UtangCount from tblUtang";
//
//           PreparedStatement ps = con.prepareStatement(sql);
//           ResultSet rs = ps.executeQuery();
//            if(rs.next()){
//                total = rs.getInt("UtangCount");
//
//           }
//            rs.close();
//            ps.close();
//            con.close();
//
//       } catch (Exception e) {
//           Log.e("SQL", "Error getting product count", e);
//       }return total ;





//    }
    public ArrayList<Product_model> getProductDetails(Context context) {

        ArrayList<Product_model> list = new ArrayList<>();

        try {

            Connection con = new connector(context).getConnection();

            String sql = "SELECT * FROM tblProduct";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product_model model = new Product_model();

                model.setProductID(
                        rs.getInt("ProductID")
                );

                model.setProductName(
                        rs.getString("ProductName")
                );

                model.setBarcode(
                        rs.getString("Barcode")
                );

                model.setCategory(
                        rs.getString("Category")
                );

                model.setPrice(
                        rs.getDouble("Price")
                );

                model.setStock(
                        rs.getInt("Stock")
                );


                list.add(model);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            Log.e(
                    "getProductDetails",
                    "Error getting products",
                    e
            );
        }

        return list;
    }

    public ArrayList<Utang_model> getUtangDetails(Context context) {

        ArrayList<Utang_model> list = new ArrayList<>();

        try {

            Connection con =
                    new connector(context).getConnection();

            String sql ="select * from Utangname";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs =ps.executeQuery();

            while (rs.next()) {

                Utang_model model =
                        new Utang_model(rs.getString("fullname"),
                                rs.getString("contactNumber"),
                                rs.getString("address"),
                                rs.getInt("id") );



                list.add(model);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            Log.e(
                    "getUtangDetails",
                    "Error getting utang details",
                    e
            );
        }

        return list;
    }



    //new person to utang
//    public boolean addNewUtang(Context context, add_new_utang_model model) {
//        // Use try-with-resources to automatically close connections and prevent memory leaks
//        String sql = "INSERT INTO tblUtang (person_name, contact_number, what_utang, amount, date_borrowed, notes) " +
//                "VALUES (?, ?, ?, ?, GETDATE(), ?)";
//
//        try (Connection con = new connector(context).getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setString(1, model.getPerson_name());
//            ps.setString(2, model.getContact_number());
//            ps.setString(3, model.getWhat_utang());
//            ps.setDouble(4, model.getAmount());
//            ps.setString(5, model.getNotes()); // This correctly maps to the 5th '?'

//            return ps.executeUpdate() > 0;
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    public Utang_model CurrentName (Context context,int id){
       Connection con= new connector(context).getConnection();
       try{
           String sql = "select * from Utangname where id =?";

           PreparedStatement ps = con.prepareStatement(sql);
           ps.setInt(1,id);
           ResultSet rs = ps.executeQuery();

           if(rs.next()){
                Utang_model model =  new Utang_model(
                        rs.getString("fullname"),
                        rs.getString("contactNumber"),
                        rs.getString("address"),
                        rs.getInt("id")
                        );
               return model;

           }

       } catch (Exception e) {
           throw new RuntimeException(e);
       }
       return null;
    }









//    public boolean UtangDetails (Context context, add_new_utang_model model){
//
//       String personName;
//        String contactNumber;
//    String balance;
//
//        try{
//           Connection con = new connector(context).getConnection();
//
//
//           String sql = "select * from Utangname";
//
//           PreparedStatement ps = con.prepareStatement(sql);
//          ResultSet rs =ps.executeQuery();
//
//           if (rs.next()){
//              personName= rs.getString("fullname");
//              contactNumber= rs.getString("contactNumber");
//               balance = rs.getString("address");
//           }
//
//       }catch (Exception e){
//           Log.e("getProductDetails","Error getting products",e);
//       }
//      return true;
//    }

//
//








    public boolean addProduct(Context context, add_product_details_models addProductModel){
       try{
           Connection con = new connector(context).getConnection();
           String sql= "insert tblProduct (ProductName,Barcode,Category,Price,Stock,DateAdded)\n" +
                   "values\n" +
                   "\t(?,?,?,?,?,GETDATE())";

           PreparedStatement ps =con.prepareStatement(sql);
            ps.setString(1,addProductModel.getProductname());
            ps.setString(2,addProductModel.getBarcode());
            ps.setString(3,addProductModel.getCategory());
            ps.setDouble(4,addProductModel.getPrice());
            ps.setInt(5,addProductModel.getStock());

            return ps.executeUpdate()>0;

       } catch (Exception e) {
           throw new RuntimeException(e);
       }






    }

    public boolean updateUtang (Context context , int id,String fullname,String contactNumber,String address){
       Connection con = new connector(context).getConnection();
try {
    String sql = "update Utangname\n" +
            "set fullname=? ,contactNumber=?,address=?\n" +
            "where id = ?";

    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1,fullname);
    ps.setString(2,contactNumber);
    ps.setString(3,address);
    ps.setInt(4,id);

    int val = ps.executeUpdate();

    if (val > 0) {

        return true;
    }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    return false;
    }

    public int UtangUpperCount (Context context){
       Connection con = new connector(context).getConnection();
       int total;
       try{
           String sql = "select count (*) as UtangCount from Utangname";


           PreparedStatement ps = con.prepareStatement(sql);

           ResultSet rs = ps.executeQuery();

           if(rs.next()){
               total=rs.getInt("UtangCount");
               return total;
           }

               } catch (Exception e) {
                   throw new RuntimeException(e);
               }
        return 0;
    }

    public boolean utangNameSql(Context context ,String fullName,String contactNumber,String address)
    {
        try {
            Connection con = new connector(context).getConnection();

            String sql = "\n" +
                    "insert into utangName \n" +
                    "(fullname,contactNumber,address)\n" +
                    "values(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,fullName);
            ps.setString(2,contactNumber);
            ps.setString(3,address);

            return ps.executeUpdate()>0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}
