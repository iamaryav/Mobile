package com.mobile.test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean  
@RequestScoped 
public class Website {
	
	String sitetitle="Mobile Stores";

	public String getSitetitle() {
		return sitetitle;
	}

	public void setSitetitle(String sitetitle) {
		this.sitetitle = sitetitle;
	}
	

}
