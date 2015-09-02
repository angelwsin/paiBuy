package com.paiBuy.util;

import java.security.MessageDigest;
/*
 * MD5的全称是Message-Digest Algorithm 5，
 * Message-Digest泛指字节串(Message)的Hash变换，
 * 就是把一个任意长度的字节串变换成一定长的大整数。
 * MD5将任意长度的"字节串"变换成一个128bit的大整数，
 * 并且它是一个不可逆的字符串变换算法，换句话说就是，
 * 即使你看到源程序和算法描述，也无法将一个MD5的值变换回原始的字符串，
 * 从数学原理上说，是因为原始的字符串有无穷多个，这有点象不存在反函数的数学函数。
 */
public class MD5Utils {
	
	public static String MD5NonEncrypt(String org){
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
		 try {
			 byte[] strTemp = org.getBytes();
			 //使用MD5创建MessageDigest对象
		          MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		          mdTemp.update(strTemp);
		      byte[] md = mdTemp.digest();
		        int j = md.length;
			char str[] = new char[j * 2];
			         int k = 0;
			         for (int i = 0; i < j; i++) {
			        	byte b = md[i];
			        	//System.out.println((int)b);
			        	//将没个数(int)b进行双字节加密
			        	str[k++] = hexDigits[b >> 4 & 0xf];
			        str[k++] = hexDigits[b & 0xf];
			         }
		 return new String(str);
			
		} catch (Exception e) {
			// TODO: handle exception
			return  null;
		}
	}

}
