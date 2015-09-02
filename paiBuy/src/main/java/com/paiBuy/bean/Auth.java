package com.paiBuy.bean;

import java.util.Date;

public class Auth {
   private String id;
   private String authName;
   private String authCode;
  private  Date  addTime;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getAuthName() {
	return authName;
}
public void setAuthName(String authName) {
	this.authName = authName;
}
public String getAuthCode() {
	return authCode;
}
public void setAuthCode(String authCode) {
	this.authCode = authCode;
}
public Date getAddTime() {
	return addTime;
}
public void setAddTime(Date addTime) {
	this.addTime = addTime;
}
  
  
}
