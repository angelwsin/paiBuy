package paiBuy;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.paiBuy.bean.User;
import com.paiBuy.dao.mapper.UserMapper;
import com.paiBuy.service.UserService;
import com.paiBuy.util.MD5Utils;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext-context.xml")
@TransactionConfiguration(defaultRollback=false)
public class UserMapperTest {

	 @Autowired
	    private UserMapper userMapper;
	  
	 @Autowired
	   private UserService userService;
      
	
	 @Test
	    public void saveUserTest(){
		     User user = new User();
		     // user.setId(Utils.UUID());
		     user.setLoginName("zhangzh");
		      user.setPassword(MD5Utils.MD5NonEncrypt("123456"));
		      user.setAddTime(new Date());
	    	 // userMapper.saveUser(user);
		     //userService.save(user);
		     // userService.update(user, "loginName");
		      userService.delete(user, "loginName");
		  try {
			//System.out.println(ObjectValue.getValue(User.class,user, "id"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    	  
	    }
	  
	  // @Test
	  public void sqlTest(){
		  
		          
//		       while(s.hasNext()){
//		    	   System.out.println(s.next());
//		       }
//		       Iterator<String> is = sqlSessionTemplate.getConfiguration().getParameterMapNames().iterator();
//		        while(is.hasNext()){
//		        	System.out.println(is.next());
//		        }
		   //    baseDao.save();
	    	     
	    	
		             
		          
	  }
}
