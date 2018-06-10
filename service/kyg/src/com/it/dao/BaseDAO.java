package com.it.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.it.configs.ConfigValues;
/***
 * 所有DAO层的父类 封装了数据库连接的相关操作 子类实现该类即可操作数据库 
 * @author Alex
 */
public class BaseDAO {
    private BasicDataSource ds ;
	/**
	 * 初始化配置数据源
	 * @return 数据源
	 */
	private BasicDataSource initDataSource() {
		BasicDataSource ds = new BasicDataSource();
		// ds.setDriverClassName("org.gjt.mm.mysql.Driver");
		ds.setDriverClassName(ConfigValues.DABASE_DRIVRE);
		ds.setUsername(ConfigValues.DABASE_USER);
		ds.setPassword(ConfigValues.DABASE_PASSWORD);
		ds.setUrl(ConfigValues
				 .DABASE_URL);
	    return ds;
	}
   /**
	 * 查找多个对象 查找多条记录
	 * @param sqlString
	 * @param clazz
	 * @return
	 */
	public List query(String sqlString, Class clazz) {
		List beans = null;
        try {
       this.ds=this.initDataSource();
			QueryRunner qRunner = new QueryRunner( this.ds);
			ResultSetHandler res = new BeanListHandler(clazz);
            beans = (List) qRunner.query(sqlString, res);
			 if (this.ds!=null) {
					this.ds.close();
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}

	/**
	 * 查找对象
	 * @param sqlString
	 * @param clazz
	 * @return
	 */
	public Object get(String sqlString, Class clazz) {
		List beans = null;
		Object obj = null;
          try {
		       this.ds=this.initDataSource();
			QueryRunner qRunner = new QueryRunner(this.ds);
			beans = (List) qRunner.query(sqlString, new BeanListHandler(clazz));
			 if (this.ds!=null) {
					this.ds.close();
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (beans != null && !beans.isEmpty()) { // 注意这里
			obj = beans.get(0);
          }
         return obj;
	}

	/**
	 * 执行更新的sql语句,插入,修改,删除
	 * 
	 * @param sqlString
	 * @return
	 */
	public boolean update(String sqlString) {

		boolean flag = false;
		try {
			this.ds=this.initDataSource();
			QueryRunner qRunner = new QueryRunner(this.ds);
			int i = qRunner.update(sqlString);
			if (i > 0) {
				flag = true;
			}
			 if (this.ds!=null) {
					this.ds.close();
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	

}
