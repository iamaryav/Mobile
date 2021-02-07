package com.mobile.test;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.Statement;  
import java.sql.ResultSet;  
import java.util.ArrayList;  
import java.util.Map;  
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.RequestScoped;  
import javax.faces.context.FacesContext;  
@ManagedBean  
@RequestScoped  
public class Edit{  
	int id;
	String name;
	String screen_size;
	String camera;
	String memory;
	String ram;
	String processor;
	ArrayList usersList ;  
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
	Connection connection;  
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScreen_size() {
		return screen_size;
	}
	public void setScreen_size(String screen_size) {
		this.screen_size = screen_size;
	}
	public String getCamera() {
		return camera;
	}
	public void setCamera(String camera) {
		this.camera = camera;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	  
// Used to establish connection  
public Connection getConnection(){  
try{  
Class.forName("com.mysql.jdbc.Driver");     
connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/smartphones","root","");  
}catch(Exception e){  
System.out.println(e);  
}  
return connection;  
} 
// fetch all records
public ArrayList usersList(){  
try{  
usersList = new ArrayList();  
connection = getConnection();  
Statement stmt=getConnection().createStatement();    
ResultSet rs=stmt.executeQuery("select * from samsung");    
while(rs.next()){  
Edit user = new Edit();  
user.setId(rs.getInt(1));
user.setName(rs.getString(2));
user.setScreen_size(rs.getString(3));
user.setCamera(rs.getString(4));
user.setMemory(rs.getString(5));
user.setRam(rs.getString(6));
user.setProcessor(rs.getString(7)); 
usersList.add(user);  
}  
connection.close();          
}catch(Exception e){  
System.out.println(e);  
}  
return usersList;  
}  

// Used to fetch record to update  
public String editss(int id){  
Edit smartphone = null;  
System.out.println(id);  
try{  
connection = getConnection();  
Statement stmt=getConnection().createStatement();    
ResultSet rs=stmt.executeQuery("select * from samsung where  model = "+(id));  
rs.next();
smartphone = new Edit();  
smartphone.setId(rs.getInt(1));
smartphone.setName(rs.getString(2));
smartphone.setScreen_size(rs.getString(3));
smartphone.setCamera(rs.getString(4));
smartphone.setMemory(rs.getString(5));
smartphone.setRam(rs.getString(6));
smartphone.setProcessor(rs.getString(7));  
sessionMap.put("d", smartphone);  
connection.close();  
}catch(Exception e){  
System.out.println(e);  
}         
return "/editmodel.xhtml?faces-redirect=true";  
}  
// Used to update user record  
public String update(Edit s){  
//int result = 0;  
try{  
connection = getConnection();    
PreparedStatement pst=connection.prepareStatement(  
"update samsung set name=?, screen_size=?, camera=?, memory=?, ram=?, processor=? where model=?");  

System.out.println(s.getId());
System.out.println(s.getName());
System.out.println(s.getCamera());
System.out.println(s.getProcessor());


pst.setString(1,s.getName());
pst.setString(2,s.getScreen_size());
pst.setString(3,s.getCamera());
pst.setString(4,s.getMemory());
pst.setString(5,s.getRam());
pst.setString(6,s.getProcessor());
pst.setInt(7,s.getId());

int rs = pst.executeUpdate(); 

System.out.println("After execute query");
connection.close(); 
if(rs != 0){

    System.out.println(rs + " row/s affected");
    
}else{
	System.out.println("No row affected");
}

}catch(Exception e){  
System.out.println(e);  
}  
return "/index.xhtml?faces-redirect=true";        
}  
} 
