package com.mobile.test;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "fetchDetails", eager = true)
@SessionScoped
public class FetchDetails implements Serializable {
   private static final long serialVersionUID = 1L;

   public Smartphone getSmartphones() {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = getConnection();
      String stm = "select * from samsung where model =1";
      //List<Smartphone> records = new ArrayList<Smartphone>();
      Smartphone smartphone = new Smartphone();
      try {   
         pst = con.prepareStatement(stm);
         pst.execute();
         rs = pst.getResultSet();

         	rs.next();
            
            smartphone.setId(rs.getInt(1));
            smartphone.setName(rs.getString(2));
            smartphone.setScreen_size(rs.getString(3));
            smartphone.setCamera(rs.getString(4));
            smartphone.setMemory(rs.getString(5));
            smartphone.setRam(rs.getString(6));
            smartphone.setProcessor(rs.getString(7));

           				
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return smartphone;
   }

   public Connection getConnection() {
	   
	    Connection con = null;
	   	String url = "jdbc:mysql://localhost:3306/smartphones";
		String user = "root";
		String password = "";
		
      
      try {
    	  try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         con = DriverManager.getConnection(url, user, password);
         System.out.println("Connection completed.");
      } catch (SQLException ex) {
         System.out.println(ex.getMessage());
      }
      
      finally {
      }
      return con;
   }
}