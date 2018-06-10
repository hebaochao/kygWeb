package com.it.dao;

import java.util.List;

import com.it.domain.Product;

public class ProductManager extends BaseDAO {
	/************************** 单例模式 *********************************/
	public static ProductManager productManager = new ProductManager();

	public static ProductManager getProductManager() {
		return productManager;
	}

	private String sqlString;

	public ProductManager() {
		// TODO Auto-generated constructor stub
	}

	/***
	 * 根据商品等级查找商品
	 * 
	 * @param rank
	 * @return
	 */
	public List<Product> getProductListByRank(int rank) {
		// TODO Auto-generated method stub
		sqlString = "select * from product where rank=" + rank;
		return super.query(sqlString, Product.class);
	}

	/***
	 * 模糊查找商品
	 * 
	 * @param str
	 * @return
	 */
	public List<Product> getProductListBylike(String str) {
		// TODO Auto-generated method stub
		sqlString = "SELECT * FROM product WHERE name LIKE '%" + str + "%'";

		return super.query(sqlString, Product.class);
	}

	/***************************** 模糊查找 *******************************/
	/***
	 * 模糊查找商品 按销量降序排序输出
	 * 
	 * @param str
	 * @return
	 */
	public List<Product> getProductListBylike_Sale_down(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE name LIKE  '%" + str
				+ "%'order by  out1  desc";
	
		return super.query(sqlString, Product.class);
	}

	/***
	 * 模糊查找商品 按销量升序排序输出
	 * 
	 * @param str
	 * @return
	 */
	public List<Product> getProductListBylike_Sale_up(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE name LIKE  '%" + str
				+ "%'order by  out1  asc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 模糊查找商品 按价格升序排序输出
	 * 
	 * @param str
	 * @return
	 */
	public List<Product> getProductListBylike_price_up(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE name LIKE  '%" + str
				+ "%'order by  price  asc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 模糊查找商品 按价格降序排序输出
	 * 
	 * @param str
	 * @return
	 */
	public List<Product> getProductListBylike_price_down(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE name LIKE  '%" + str
				+ "%'order by  price  desc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 模糊查找商品 按上架时间降序排序输出
	 * 
	 * @param str
	 * @return
	 */
	public List<Product> getProductListBylike_cre_date_down(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE name LIKE  '%" + str
				+ "%'order by  cre_date  desc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 模糊查找商品 按上架时间升序排序输出
	 * 
	 * @param str
	 * @return
	 */
	public List<Product> getProductListBylike_cre_date_up(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE name LIKE  '%" + str
				+ "%'order by  cre_date  asc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/******************************* 模糊查找 *******************************************/

	/***
	 * 按分类（一级分类） 以销量降序查找商品列表
	 * 
	 */
	public List<Product> getProductListByPro_category_Sale_down(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE pro_category_id='" + str
				+ "' order by  out1  desc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 按类型（二级分类） 以销量降序查找商品列表
	 * 
	 */
	public List<Product> getProductListByPro_type_Sale_down(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE pro_type_id='" + str
				+ "' order by  out1  desc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	
	
	
	
	
	
	
	/***
	 * 按品牌（三级分类） 以销量降序查找商品列表
	 * 
	 */
	public List<Product> getProductListByBrand_Sale_down(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE brand_id='" + str
				+ "' order by  out1  desc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/*********************************************/

	
	
	
	
	/***
	 * 按分类（一级分类） 以销量升序查找商品列表
	 * 
	 */
	public List<Product> getProductListByPro_category_Sale_up(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE pro_category_id='" + str
				+ "' order by  out1  asc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 按类型（二级分类） 以销量降序查找商品列表
	 * 
	 */
	public List<Product> getProductListByPro_type_Sale_up(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE pro_type_id='" + str
				+ "' order by  out1  asc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	
	
	
	
	
	
	
	/***
	 * 按品牌（三级分类） 以销量降序查找商品列表
	 * 
	 */
	public List<Product> getProductListByBrand_Sale_up(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE brand_id='" + str
				+ "' order by  out1  asc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/*********************************************/
	
	
	
	
	
	
	
	
	
	
	
	/***
	 * 按分类（一级分类） 以价格升序查找商品列表
	 * 
	 */
	public List<Product> getProductListByPro_category_price_up(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE pro_category_id='" + str
				+ "' order by  price  asc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 按类型（二级分类） 以价格升序查找商品列表
	 * 
	 */
	public List<Product> getProductListByPro_type_price_up(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE pro_type_id='" + str
				+ "' order by  price  asc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 按品牌（三级分类） 以价格升序查找商品列表
	 * 
	 */
	public List<Product> getProductListByBrand_price_up(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE brand_id='" + str
				+ "' order by  price  asc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/**************************************************************/

	/***
	 * 按分类（一级分类） 以价格降序查找商品列表
	 * 
	 */
	public List<Product> getProductListByPro_category_price_down(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE pro_category_id='" + str
				+ "' order by  price  desc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 按类型（二级分类） 以价格降序查找商品列表
	 * 
	 */
	public List<Product> getProductListByPro_type_price_down(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE pro_type_id='" + str
				+ "' order by  price  desc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 按品牌（三级分类） 以价格降序查找商品列表
	 * 
	 */
	public List<Product> getProductListByBrand_price_down(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE brand_id='" + str
				+ "' order by  price  desc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/*****************************************************************/

	
	
	
	
	
	
	/***
	 * 按分类（一级分类） 以上架时间升序查找商品列表
	 * 
	 */
	public List<Product> getProductListByPro_category_cre_date_up(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE pro_category_id='" + str
				+ "' order by  cre_date  asc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 按类型（二级分类） 以上架时间升序查找商品列表
	 * 
	 */
	public List<Product> getProductListByPro_type_cre_date_up(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE pro_type_id='" + str
				+ "' order by  cre_date  asc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 按品牌（三级分类） 以上架时间升序查找商品列表
	 * 
	 */
	public List<Product> getProductListByBrand_cre_date_up(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE brand_id='" + str
				+ "' order by  cre_date  asc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/**************************************************************/

	/***
	 * 按分类（一级分类） 以上架时间降序查找商品列表
	 * 
	 */
	public List<Product> getProductListByPro_category_cre_date_down(String str) {
		

		sqlString = "SELECT * FROM product WHERE pro_category_id='" + str
				+ "' order by  cre_date  desc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 按类型（二级分类） 以价上架时间序查找商品列表
	 * 
	 */
	public List<Product> getProductListByPro_type_cre_date_down(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE pro_type_id='" + str
				+ "' order by  cre_date  desc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/***
	 * 按品牌（三级分类） 以上架时间降序查找商品列表
	 * 
	 */
	public List<Product> getProductListByBrand_cre_date_down(String str) {
		// TODO Auto-generated method stub
		// SELECT * FROM product WHERE name LIKE '%4%' order by out1 desc
		sqlString = "SELECT * FROM product WHERE brand_id='" + str
				+ "' order by  cre_date  desc";
		System.out.println(sqlString);
		return super.query(sqlString, Product.class);
	}

	/*****************************************************************/
	/***
	 * 根据商品ID获取商品
	 * 
	 */
	public Product getProductByPro_id(String pro_id) {
	 
		sqlString = "SELECT * FROM product WHERE pro_id='" + pro_id + "'";
		System.out.println(sqlString);
		return (Product) super.get(sqlString, Product.class);
	}
	
	/**********************分页查询数据 扩展方法******************************/
	/**************************** 分页查询商品 **********************************/

	/***
	 * 分页查询商品 根据一级目录
	 * 
	 * @param pro_category_id
	 * @param start
	 *            起始位置
	 * @param count
	 *            结束位置
	 * @return
	 */
	public List<Product> getProductListByPro_category_id(int pro_category_id,
			int start, int count) {
	String	sql = "select * from product   where ( pro_category_id = "
				+ pro_category_id + " )limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/****
	 * 分页查询商品 根据二级目录
	 * 
	 * @param pro_type_id
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Product> getProductListByPro_Type_id(int pro_type_id,
			int start, int count) {
		String	sql = "select * from product   where ( pro_type_id = " + pro_type_id
				+ " )limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/****
	 * 根据三级目录 分页查询商品
	 * 
	 * @param pro_brand_id
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Product> getProductListByPro_brand_id(int brand_id, int start,
			int count) {

		String  sql= "select * from product   where ( brand_id = " + brand_id
				+ " )limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询 模糊查找商品
	 * 
	 * @param str
	 * @return
	 */
	public List<Product> getProductListBylike(String str, int start, int count) {
		// TODO Auto-generated method stub
		String  sql = "SELECT * FROM product WHERE ( name LIKE '%" + str
				+ "%' )   limit " + start + "," + count;

		return super.query(sql, Product.class);
	}

	/***************************** 所有商品查询方式 *********************************/
	
	/***
	 * 无分页查询获取所有商品
	 * 
	 * @return
	 */
	public List<Product> getallproducts() {
		String  sql = "SELECT * FROM product ";

		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取所有商品
	 * 
	 * @return
	 */
	public List<Product> getallproducts(int start, int count) {
		String  sql = "SELECT * FROM product  limit " + start + "," + count;

		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取所有商品 价格降序
	 * 
	 * @return
	 */
	public List<Product> getallproducts_sort_price_down(int start, int count) {
		String  sql = "SELECT * FROM product   order by  price  desc  limit " + start + "," + count;

		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取所有商品 价格升序
	 * 
	 * @return
	 */
	public List<Product> getallproducts_sort_price_up(int start, int count) {
		String  sql = "SELECT * FROM product   order by  price  asc limit " + start + "," + count;

		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取所有商品 销量降序
	 * 
	 * @return
	 */
	public List<Product> getallproducts_sort_sale_down(int start, int count) {
		String  sql = "SELECT * FROM product   order by  out1  desc limit " + start + "," + count;

		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取所有商品 销量升序
	 * 
	 * @return
	 */
	public List<Product> getallproducts_sort_sale_up(int start, int count) {
		String  sql = "SELECT * FROM product   order by  out1  asc  limit " + start + "," + count;

		return super.query(sql, Product.class);
	}

	/*********************** 根据不同条件查询商品 *******************************/

	/***
	 * 查询商品 根据一级目录
	 * 
	 * @param pro_category_id
	 * @param start
	 *            起始位置
	 * @param count
	 *            结束位置
	 * @return
	 */
	public List<Product> getProductListByPro_category_id(int pro_category_id) {
		String  sql = "select * from product   where pro_category_id = "
				+ pro_category_id;
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取一级商品 价格降序
	 * 
	 * @return
	 */
	public List<Product> getOneproducts_sort_price_down(int pro_category_id,
			int start, int count) {

		String  sql = "select * from product   where pro_category_id = "
				+ pro_category_id +" order by  price  desc   limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取一级商品 价格升序
	 * 
	 * @return
	 */
	public List<Product> getOneproducts_sort_price_up(int pro_category_id,
			int start, int count) {

		String  sql = "select * from product   where pro_category_id = "
				+ pro_category_id + "  order by  price  asc  limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取一级商品 销量降序
	 * 
	 * @return
	 */
	public List<Product> getOneproducts_sort_sale_down(int pro_category_id,
			int start, int count) {

		String  sql = "select * from product   where pro_category_id = "
				+ pro_category_id + " order by  out1  desc   limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取一级商品 销量升序
	 * 
	 * @return
	 */
	public List<Product> getOneproducts_sort_sale_up(int pro_category_id,
			int start, int count) {

		String  sql = "select * from product   where pro_category_id = "
				+ pro_category_id + " order by  out1  asc  limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/***************** 查询二级商品 ********************/

	/****
	 * 查询商品 根据二级目录
	 * 
	 * @param pro_type_id
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Product> getProductListByPro_Type_id(int pro_type_id) {
		String  sql = "select * from product   where  pro_type_id = " + pro_type_id;
		System.out.println(sql);
		return super.query(sql, Product.class);
	}
	
	
	/***
	 * 分页查询获取二级商品 价格降序
	 * 
	 * @return
	 */
	public List<Product> getTwoproducts_sort_price_down(int pro_type_id,
			int start, int count) {

		String  sql = "select * from product   where pro_type_id = "
				+ pro_type_id + " order by  price  desc   limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取二级商品 价格升序
	 * 
	 * @return
	 */
	public List<Product> getTwoproducts_sort_price_up(int pro_type_id,
			int start, int count) {

		String  sql = "select * from product   where pro_type_id = "
				+ pro_type_id + " order by  price  asc  limit " + start + "," + count;
		
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取二级商品 销量降序
	 * 
	 * @return
	 */
	public List<Product> getTwoproducts_sort_sale_down(int pro_type_id,
			int start, int count) {

		String  sql = "select * from product   where pro_type_id = "
				+ pro_type_id + "  order by  out1  desc  limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取二级商品 销量升序
	 * 
	 * @return
	 */
	public List<Product> getTwoproducts_sort_sale_up(int pro_type_id,
			int start, int count) {

		String  sql = "select * from product   where pro_type_id = "
				+ pro_type_id + " order by  out1  asc   limit " + start + "," + count  ;
		return super.query(sql, Product.class);
	}
	
	
	
	
	/***********查询三级商品*****************/
	
	
	

	/****
	 * 根据三级目录 查询商品
	 * 
	 * @param pro_brand_id
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Product> getProductListByPro_brand_id(int brand_id) {

		String  sql = "select * from product   where brand_id = " + brand_id;
		return super.query(sql, Product.class);
	}

	
	
	/***
	 * 分页查询获取三级商品 价格降序
	 * 
	 * @return
	 */
	public List<Product> getThreeproducts_sort_price_down(int brand_id,
			int start, int count) {

		String  sql = "select * from product   where brand_id = "
				+ brand_id + "  order by  price  desc   limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取三级商品 价格升序
	 * 
	 * @return
	 */
	public List<Product> getThreeproducts_sort_price_up(int brand_id,
			int start, int count) {

		String  sql = "select * from product   where brand_id = "
				+ brand_id + " order by  price  asc   limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取三级商品 销量降序
	 * 
	 * @return
	 */
	public List<Product> getThreeproducts_sort_sale_down(int brand_id,
			int start, int count) {

		String  sql= "select * from product   where brand_id = "
				+ brand_id + "  order by  out1  desc  limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取三级商品 销量升序
	 * 
	 * @return
	 */
	public List<Product> getThreeproducts_sort_sale_up(int brand_id,
			int start, int count) {

		String  sql= "select * from product   where brand_id = "
				+ brand_id + " order by  out1  asc  limit " + start + "," + count;
		return super.query(sql, Product.class);
	}
	
	
	
	
	
	
	
	
	
	/*******************************模糊查找商品************************************/
	
	

	
	
	/***
	 * 分页查询获取模糊商品 价格降序
	 * 
	 * @return
	 */
	public List<Product> getLikeproducts_sort_price_down(String str,
			int start, int count) {

		String  sql= "select * from product   where name LIKE '%" + str + "%' order by  price  desc  limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取模糊商品 价格升序
	 * 
	 * @return
	 */
	public List<Product> getLikeproducts_sort_price_up(String str,
			int start, int count) {

		String  sql= "select * from product   where name LIKE '%" + str + "%' order by  price  asc  limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取模糊商品 销量降序
	 * 
	 * @return
	 */
	public List<Product> getLikeproducts_sort_sale_down(String str,
			int start, int count) {

		String  sql= "select * from product   where name LIKE '%" + str + "%' order by  out1  desc   limit " + start + "," + count;
		return super.query(sql, Product.class);
	}

	/***
	 * 分页查询获取模糊商品 销量升序
	 * 
	 * @return
	 */
	public List<Product> getLikeproducts_sort_sale_up(String str,
			int start, int count) {

		String  sql= "select * from product  where name LIKE '%" + str + "%'  order by  out1  asc   limit " + start + "," + count;
		return super.query(sql, Product.class);
	}
	
	
	
	

}
