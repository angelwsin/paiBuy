package paiBuy;

import org.junit.Test;

import com.paiBuy.util.MD5Utils;


public class UtilsTest {

	@Test
 public void testUUid(){
		  //  System.out.println(Utils.UUID());  
		 System.out.println(MD5Utils.MD5NonEncrypt("000000000")); 
	   }

}
