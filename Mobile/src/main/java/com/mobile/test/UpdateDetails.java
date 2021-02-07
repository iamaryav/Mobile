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

@ManagedBean(name = "updateDetails", eager = true)
@SessionScoped
public class UpdateDetails implements Serializable {
   private static final long serialVersionUID = 1L;

   public String update(Smartphone s) {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = getConnection();
      String stm = "update samsung set name=?, screen_size=?, camera=?, memory=?, ram=?, procesor=? where id=?" ;
      //List<Smartphone> records = new ArrayList<Smartphone>();
      //Smartphone smartphone = new Smartphone();
      try {   
         pst = con.prepareStatement(stm);
         pst.setString(1,s.getName());
         pst.setString(2,s.getScreen_size());
         pst.setString(3,s.getCamera());
         pst.setString(4,s.getMemory());
         pst.setString(5,s.getRam());
         pst.setString(6,s.getProcessor());
         pst.setInt(7,s.getId());
         
         
         pst.executeUpdate();  

       
            
           

           				
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return "/index.xhtml?faces-redirect=true";
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