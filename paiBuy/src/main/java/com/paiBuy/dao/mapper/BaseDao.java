package com.paiBuy.dao.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.ResultMapping;
import org.apache.logging.log4j.LogManager;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paiBuy.util.ObjectValue;

@Component
public class BaseDao<T> extends SqlSessionDaoSupport{

	  private  static org.apache.logging.log4j.Logger  log = LogManager.getLogger(BaseDao.class);
	    @Autowired
		public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
			 super.setSqlSessionTemplate(sqlSessionTemplate);
		}



		public void save(T entry,Class<?> clazz){
	    	  List<ResultMapping> list = getSqlSession().getConfiguration().getResultMap(clazz.getSimpleName()).getIdResultMappings();
		         StringBuffer  sql = new StringBuffer();
		         StringBuffer  values = new StringBuffer();
		         sql.append("insert into ").append(clazz.getSimpleName()).append(" ( ");
		         values.append(" ) values ( ");
		             for(ResultMapping p:list){
		            	     sql.append(p.getColumn()).append(" ,");
		            	     values.append("?").append(" ,");
		             }
		             sql.deleteCharAt(sql.lastIndexOf(","));
		             values.deleteCharAt(values.lastIndexOf(","));
		             values.append(" )");
		             sql.append(values.toString());
		             log.info(sql.toString());
		           Connection conn  =      getSqlSession().getConnection();
	    	           try {
	    	        	   PreparedStatement  stm = 		conn.prepareStatement(sql.toString());
	    	        	  for(int i=0;i<list.size();i++){
	    	        		   String javaType = list.get(i).getJavaType().getName();
	    	        ObjectValue.preparedOp(i+1, javaType, clazz, entry, list.get(i).getProperty(), stm);
			            }
	    	        	  stm.execute();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	      }
		public void update(T entry,Class<?> clazz,String ... where){
			List<ResultMapping> list = getSqlSession().getConfiguration().getResultMap(clazz.getSimpleName()).getIdResultMappings();
			 StringBuffer  sql = new StringBuffer();
	         sql.append("update  ").append(clazz.getSimpleName()).append(" set ");
	         try {
				for(ResultMapping p:list){
					 Object obj = ObjectValue.getValue(clazz, entry, p.getProperty());
					 if(obj!=null){
						   sql.append(p.getColumn()).append("  =   ? , ");
					 }
                   }
				sql.deleteCharAt(sql.lastIndexOf(","));
				sql.append(" where 1 = 1 ");
				for(String w:where){
					for(ResultMapping p:list){
						   if(p.getProperty().equals(w)){
							   sql.append(" and ").append(p.getColumn()).append(" = ? ");
						   }
					}
				}
				 Connection conn  =    getSqlSession().getConnection();
				 PreparedStatement stm =   conn.prepareStatement(sql.toString());
				 int k = 1;
				 for(ResultMapping p:list){
					 Object obj = ObjectValue.getValue(clazz, entry, p.getProperty());
					 if(obj!=null){
	        ObjectValue.preparedOp(k++, p.getJavaType().getTypeName(), clazz, entry, p.getProperty(), stm);
					 }
                   }
				 for(String w:where){
						for(ResultMapping p:list){
							   if(p.getProperty().equals(w)){
								  ObjectValue.preparedOp(k++, p.getJavaType().getTypeName(), clazz, entry, w, stm);
							   }
						}
					}
				 stm.executeUpdate();
				System.out.println(sql.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void delete(T entry,Class<?> clazz,String ... where){
			List<ResultMapping> list = getSqlSession().getConfiguration().getResultMap(clazz.getSimpleName()).getIdResultMappings();
			 StringBuffer  sql = new StringBuffer();
	         sql.append("delete from   ").append(clazz.getSimpleName()).append("  ");
				sql.append(" where 1 = 1 ");
				for(String w:where){
					for(ResultMapping p:list){
						   if(p.getProperty().equals(w)){
							   sql.append(" and ").append(p.getColumn()).append(" = ? ");
						   }
					}
				}
				try {
					Connection conn  =    getSqlSession().getConnection();
					 PreparedStatement stm =   conn.prepareStatement(sql.toString());
					 int k = 1;
					 for(String w:where){
							for(ResultMapping p:list){
								   if(p.getProperty().equals(w)){
									  ObjectValue.preparedOp(k++, p.getJavaType().getTypeName(), clazz, entry, w, stm);
								   }
							}
						}
					 stm.execute();
						System.out.println(sql.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
}
