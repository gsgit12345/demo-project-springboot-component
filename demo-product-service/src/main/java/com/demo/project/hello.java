package com.demo.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class hello {
    public static void main(String str[]) {

        //    kafkademo.default.svc.cluster.local:32069
        //172.17.0.2
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connection con= DriverManager.getConnection(
            //       "jdbc:mysql://10.43.32.129:3306/Employee","root","root");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://mysql.default.svc.cluster.local:30007/Employee", "root", "root");
//here sonoo is database name, root is username and password
            //  mysql.default.svc.cluster.local:30007
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from t_inventory");
            while (rs.next())
                System.out.println("hello mysql ::" + rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//http://localhost:8081/course/save
}

