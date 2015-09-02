package com.paiBuy.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObjectValue {
	
	
	  public static  Object  getValue(Class<?> clazz,Object obj,String property)throws Exception{
		                                if(obj==null||property.isEmpty()){
		                                	throw new Exception("非法的参数!");
		                                }
		                                        try {
										Method  method = clazz.getMethod("get"+property.substring(0, 1).toUpperCase()+property.substring(1));
										            return    method.invoke(obj);
												}catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
		                                        return null;
	  }
	  
	  public  static List<String>  getObjectProperties(Class<?> clazz){
		                           Field[] fields    =   clazz.getDeclaredFields();
		                            List<String> list =  new ArrayList<String>();
		                            for(Field field:fields){
		                            	  list.add(field.getName());
		                            }
		                            return list;
	  }
	  
	  public static void  preparedOp(int index,String javaType,Class<?> clazz,Object entry,String property,PreparedStatement stm)throws Exception{
		  if(String.class.getName().equals(javaType)){
			   stm.setString(index,ObjectValue.getValue(clazz, entry, property).toString() );
		   }
		   if(Date.class.getName().equals(javaType)){
			   long  date = ((Date)ObjectValue.getValue(clazz, entry, property)).getTime();
			   stm.setDate(index, new java.sql.Date(date));
		   }
		   if(int.class.getName().equals(javaType)){
			   stm.setInt(index, (Integer)ObjectValue.getValue(clazz, entry, property));
		   }
	  }

}
