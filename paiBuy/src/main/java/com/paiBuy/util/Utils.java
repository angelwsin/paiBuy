package com.paiBuy.util;

public class Utils {

	  public static String UUID(){
		        return  java.util.UUID.randomUUID().toString().replace("-", "");
	  }
	  
	  
	  public static boolean isEmpty(String str){
		       return str==null || str.length()==0;
	  }
	
}
