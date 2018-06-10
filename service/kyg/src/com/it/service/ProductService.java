package com.it.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.IsEqual;

import com.it.Util.FastjsonTools;
import com.it.configs.ConfigValues;
import com.it.dao.ProductManager;
import com.it.domain.Product;
import com.it.domain.Product_page;

public class ProductService {

	public String Produts_keyName="products";
	public String Produt_keyName="product";
	public String Produt_page_keyName="product_page";
	/******************** 单例模式 ******************************/
	public static ProductService productService = new ProductService();

	public static ProductService getProductService() {
		return productService;
	}

	public ProductService() {

	}

	

	/*****
	 * 创建返回json
	 * @param produts  list集合
	 * @return
	 */
	private Map<String, Object> creatResultMes(List<Product> produts){
		  Map<String, Object> map=new HashMap<String, Object>();
				if (produts.size()>0) {
					map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
					map.put(this.Produts_keyName,produts);
				}else{
					map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_ERROR);
				}
				return map;
				
	}
	/*****
	 * 创建返回json
	 * @param produt  商品对象
	 * @return
	 */
	private Map<String, Object> creatResultObject(Object produt){
		  Map<String, Object> map=new HashMap<String, Object>();
		
		  
		  if (produt!= null) {
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
				
			}else{
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_ERROR);
			}
		  
		  
		  
		  if (produt instanceof Product) {
			  map.put(this.Produt_keyName,produt);
		    }else if(produt instanceof Product_page){
		    	 map.put(this.Produt_page_keyName,produt);
		    }
		  
		  
				
				return map;
				
	}
	
	/***
	 * 获取热卖商品列表
	 * 
	 * @return
	 */
	public String GetHotProduct() {
		
		List<Product> produts=ProductManager
		.getProductManager().getProductListByRank(1);
		
		
		return FastjsonTools.CreategsonString(this.creatResultMes(produts));
	}

	/***
	 * 获取最新商品列表
	 * 
	 * @return
	 */
	public String GetNewsProduct() {
	
		return FastjsonTools.CreategsonString(this.creatResultMes(ProductManager
				.getProductManager().getProductListByRank(2)));
	}

	/***
	 * 获取限时特价商品列表
	 * 
	 * @return
	 */
	public String GetScalProduct() {
		return FastjsonTools.CreategsonString(this.creatResultMes(ProductManager
				.getProductManager().getProductListByRank(3)));
		
	}

	/***
	 * 获取每日抢购商品列表
	 * 
	 * @return
	 */
	public String GetEveryOneProduct() {
		return FastjsonTools.CreategsonString(this.creatResultMes(ProductManager
				.getProductManager().getProductListByRank(4)));
	
	}

	/***
	 * 模糊查找商品列表
	 *   无实现分页
	 * @return
	 */
	public String SearchLikeProduct(String str, int type) {
		
		List<Product> list=null;
		switch (type) {
		case 11:// 销量降序( 11)（ 默认）
			list=ProductManager
					.getProductManager().getProductListBylike_Sale_down(str);
		
			break;
		case 12:// 销量升序(12)
			list=ProductManager
					.getProductManager().getProductListBylike_Sale_up(str);
		
			break;
		case 21:// 价格降序(21)
			list=ProductManager
					.getProductManager().getProductListBylike_price_down(str);
			
			break;
		case 22:// 价格升序（22）
			list=ProductManager
					.getProductManager().getProductListBylike_price_up(str);
			
			break;
		case 31:// 时间降序(31)
			list=ProductManager
					.getProductManager()
					.getProductListBylike_cre_date_down(str);
			
			break;
		case 32:// 时间升序（32）
			list=ProductManager
					.getProductManager().getProductListBylike_cre_date_up(str);
		
			break;

		}

		return FastjsonTools.CreategsonString(this.creatResultMes(list));

	}

	/***
	 * 无分页
	 * 第一位是区别菜单等级  第二位数字是0 销量升   1 销量降 2价格升 3价格降 4 上架日期升 5上架日期降
	 * 根据type分别查找一级、二级、三级目录商品 并按销量降序、价格升序、价格降序排序查找商品列表 一级+销量降序( 11) 二级+销量降序(21)
	 * 三级+按销量降序（31） 一级+价格升序(12) 二级+价格升序(22) 三级+价格升序（32） 三级+价格降序(13) 二级+价格降序(23)
	 * 三级+价格降序（33）
	 * @param str
	 * @return 返回符合要求的商品集合
	 */
	public String GetProductList(String id, int key) {
		List<Product> list = null;
		switch (key) {
		case 10:
			list = ProductManager.getProductManager()
					.getProductListByPro_category_Sale_up(id);
			break;
		case 11:
			list = ProductManager.getProductManager()
					.getProductListByPro_category_Sale_down(id);
			break;

		case 12:
			list = ProductManager.getProductManager()
					.getProductListByPro_category_price_up(id);
			break;
		case 13:
			list = ProductManager.getProductManager()
					.getProductListByPro_category_price_down(id);
			break;

			
		case 14:
			list = ProductManager.getProductManager()
					.getProductListByPro_category_cre_date_up(id);
			break;
		case 15:
			list = ProductManager.getProductManager()
			.getProductListByPro_category_cre_date_down(id);
			break;
			
			
			
			
			
			
			
		case 20:
			list = ProductManager.getProductManager()
					.getProductListByPro_type_Sale_up(id);
			break;
		case 21:
			list = ProductManager.getProductManager()
					.getProductListByPro_type_Sale_down(id);
			break;
		case 22:
			list = ProductManager.getProductManager()
					.getProductListByPro_type_price_up(id);
			break;
		case 23:
			list = ProductManager.getProductManager()
					.getProductListByPro_type_price_down(id);
			break;

		case 24:
			list = ProductManager.getProductManager()
					.getProductListByPro_type_cre_date_up(id);
			break;
		case 25:
			list = ProductManager.getProductManager()
			.getProductListByPro_type_cre_date_down(id);
			break;	
			
			
			
			
			
			
			
			
		case 30:
			list = ProductManager.getProductManager()
					.getProductListByBrand_Sale_up(id);
			break;
		case 31:
			list = ProductManager.getProductManager()
					.getProductListByBrand_Sale_down(id);
			break;

		case 32:
			list = ProductManager.getProductManager()
					.getProductListByBrand_price_up(id);
			break;
		case 33:
			list = ProductManager.getProductManager()
					.getProductListByBrand_price_down(id);
			break;
			
			
		case 34:
			list = ProductManager.getProductManager()
					.getProductListByBrand_cre_date_up(id);
			break;
		case 35:
			list = ProductManager.getProductManager()
					.getProductListByBrand_cre_date_down(id);
			break;	
		}

		
	
		return FastjsonTools.CreategsonString(this.creatResultMes(list));
	

	}

	/****
	 * 根据ID查找商品
	 */
	public String getProductById(String pid) {
		
		return FastjsonTools.CreategsonString(this.creatResultObject(ProductManager
				.getProductManager().getProductByPro_id(pid)));

	}

	public String GetProductList(String[] strs) {
		if (strs.length == 0) {
			return null;
		}

		List<Product> products = new ArrayList<Product>();
		for (String string : strs) {
			
			Product product = ProductManager.getProductManager()
					.getProductByPro_id(string);
		
			if (product != null) {
				products.add(product);
			}
		}
		return FastjsonTools.CreategsonString(this.creatResultMes(products));
		
	}
	
	
	/***************分页查找商品列表处理 扩展方法***************************/
	
	
	

	
	
	
	/***
	 * 分页產找商品
	 * 第一位是区别菜单等级   (其中 4是模糊查找) 第二位数字是0 销量升   1 销量降 2价格升 3价格降 4 上架日期升 5上架日期降
	 * 根据type分别查找一级、二级、三级目录商品 并按销量降序、价格升序、价格降序排序查找商品列表 一级+销量降序( 11) 二级+销量降序(21)
	 * 三级+按销量降序（31） 一级+价格升序(12) 二级+价格升序(22) 三级+价格升序（32） 三级+价格降序(13) 二级+价格降序(23)
	 * 三级+价格降序（33）
	 * @param str
	 * @return 返回符合要求的商品集合
	 */
	public String GetProductList(String id, int key,int req_page,String likeStr) {
		
		
		if(req_page<=0){//页码如果小于0则返回
			return FastjsonTools.CreategsonString( this.creatResultObject(null));
		}
	
		
		//创建分页商品列表模型对象
		Product_page pro_page=new Product_page();
		
		
		//计算类型码
	  int  type=(int) (key*0.1);
	  
	  int pid=0;//判断是否模糊查询

	  if(likeStr==null){
			//转化商品ID
		  pid=Integer.parseInt(id);
	  }
	

           //计算总页数
		pro_page.count_page=this.getCountPageNumber( pid, type, likeStr);
		
		if(req_page>pro_page.count_page){//訪問頁數大於总页数
			return FastjsonTools.CreategsonString( this.creatResultObject(null));
		}
		
		//设置请求页
		pro_page.cur_page=req_page;
	
	
		//获取商品记录总条数
		int alltotalPage = this.CountProductById( pid, type, likeStr);

		//每次只查询10条记录
		int count =10;
		
		int startPoint = req_page * 10-10; // 新查询的起始位置

		if (startPoint + 10 > alltotalPage) { // 小于10条记录
		
			count= alltotalPage - startPoint;
	
		}
		
		 //计算排序方式码
			int sortType=key%10;
		
		
		//获取请求页的商品列表
		switch (type) {
		
		

		case 0:// 所有商品
			pro_page.products = this.getAllProductsSort(sortType, pid, startPoint, count);
			break;
		
		case 1:// 一级商品
			pro_page.products= this.getOneProductsSort(sortType, pid, startPoint, count);
			break;
		case 2:// 二级商品
			pro_page.products = this.getTwoProductsSort(sortType, pid, startPoint, count);
			break;
		case 3:// 三级商品
			pro_page.products= this.getThreeProductsSort(sortType, pid, startPoint, count);
			break;
		case 4:// 模糊搜索
			pro_page.products = this.getLikeProductsSort(sortType, likeStr, startPoint, count);
			break;
			
		}
		
		
		return FastjsonTools.CreategsonString( this.creatResultObject(pro_page));
		

	}
	
	
	
	

	
	
	
	
	
	/*
	 * 计算总页数
	 * 以每页显示10条数据计算
	 * @pid 商品ID
	 * @type 操作类型
	 * */
	public  int getCountPageNumber(int pid, int type, String str){
	
		
		//计算查找的商品数量（总记录数）
	int totolPage=	this.CountProductById(pid, type, str);
	//计算总页数(返回页码列表 获取总页数)

	if(totolPage%10!=0){//整数页
		return totolPage/10+1;
	}else {
		return  totolPage/10;
	}
	
	}
	
	
	
	
	
	
	/****
	 * 根据类型 计算商品的总条目数 type： 第一位数字 代表查找类型 0 所有商品 1 一级目录 2 二级目录 3 第二位数字代表
	 * 
	 * @param pid
	 * @param type
	 * @param str
	 * @return
	 */

	public int CountProductById(int pid, int type, String str) {

		int totalPage = -1;

		switch (type) {
		case 0:// 所有商品的
			totalPage = ProductManager.getProductManager().getallproducts().size();
			break;
		case 1:// 1 一级目录
			totalPage = ProductManager.getProductManager()
					.getProductListByPro_category_id(pid).size();
			break;
		case 2:// 2 二级目录
			totalPage = ProductManager.getProductManager()
					.getProductListByPro_Type_id(pid).size();
			break;
		case 3:// 2 三级目录
			totalPage = ProductManager.getProductManager()
					.getProductListByPro_brand_id(pid).size();
			break;
		case 4:// 模糊查找
			totalPage = ProductManager.getProductManager()
					.getProductListBylike(str).size();
			break;
		}

		return totalPage;

	}

//	/****
//	 * 根据请求页码 总页数 计算页码列表
//	 * 
//	 * @param page
//	 * @param totalPage
//	 * @return
//	 */
//	public List<Integer> getpropageListByIdandType(int page, int totalPage) {
//		// TODO Auto-generated method stub
//
//		List<Integer> pageArr = new ArrayList<Integer>(); // 页数列表
//
//		int start = 1; // 页码小于10起始 值
//		if (page >= 9) { // 页码大于10的起始值
//			start = (int) (page / 10.0 * 10);
//		}
//
//		int num = start;
//		while (!(num > totalPage || num > start + 9)) { // 起始页码小于总页数 且
//														// 只显示起始页码+10的范围
//														// 值显示10个页码
//			pageArr.add(num);
//			++num;
//		}
//
//		return pageArr;
//	}

//	/****
//	 * 分页查询 查询指定页面的商品列表 排序类型 0默认 无排序 1价格由低到高 2价格由高到低 3销量从低到高 4销量从高到低
//	 * 
//	 * @param id
//	 * @param page
//	 * @param type
//	 * @param str
//	 * @return
//	 */
//	public List<Product> getProductListByPro_id_type(int id, int page,
//			int type, String str, int sort) {
//		int alltotalPage = this.CountProductById(id, type, str);
//
//		int startPoint = page * 9 - 9; // 新查询的起始位置
//		int count = 9;
//		if (startPoint + 9 > alltotalPage) { // 小于10条记录
//			// count = 9 - (startPoint - totalPage);
//			count = alltotalPage - startPoint;
//		}
//		List<Product> products = null;
//
//		switch (type) {
//		case 0:// 所有商品
//			products = this.getAllProductsSort(sort, id, startPoint, count);
//			break;
//		
//		case 1:// 一级商品
//			products = this.getOneProductsSort(sort, id, startPoint, count);
//			break;
//		case 2:// 二级商品
//			products = this.getTwoProductsSort(sort, id, startPoint, count);
//			break;
//		case 3:// 三级商品
//			products = this.getThreeProductsSort(sort, id, startPoint, count);
//			break;
//		case 4:// 模糊搜索
//			products = this.getLikeProductsSort(sort, str, startPoint, count);
//			break;
//
//		}
//	
//		return products;
//	}

	
	
	
	
	/****
	 * 根据不同的排序方式 分页查找所有商品 排序类型 0默认 无排序 1价格由低到高 2价格由高到低 3销量从低到高 4销量从高到低
	 * 
	 * @param key
	 * @param id
	 * @param startPoint
	 * @param count
	 * @return
	 */
	private List<Product> getAllProductsSort(int key, int id, int startPoint,
			int count) {

		switch (key) {
		case 0:// 所有商品默认查询
			return ProductManager.getProductManager().getallproducts(
					startPoint, count);
		case 1: // 价格升序
			return ProductManager.getProductManager()
					.getallproducts_sort_price_up(startPoint, count);

		case 2:// 價格降序
			return ProductManager.getProductManager()
					.getallproducts_sort_price_down(startPoint, count);

		case 3:// 销量升序

			return ProductManager.getProductManager()
					.getallproducts_sort_sale_up(startPoint, count);
		case 4:// 销量降序

			return ProductManager.getProductManager()
					.getallproducts_sort_sale_down(startPoint, count);
		default:
			return ProductManager.getProductManager().getallproducts(
					startPoint, count);
		}

	}

	/***
	 * 获取一级目录下的商品 根据不同的排序方式
	 * 
	 * @param key
	 * @param id
	 * @param startPoint
	 * @param count
	 * @return
	 */
	private List<Product> getOneProductsSort(int key, int id, int startPoint,
			int count) {

		switch (key) {
		case 0:// 一级商品默认查询
			return ProductManager.getProductManager()
					.getProductListByPro_category_id(id, startPoint, count);
		case 1: // 价格升序
			return ProductManager.getProductManager()
					.getOneproducts_sort_price_up(id, startPoint, count);

		case 2:// 價格降序
			return ProductManager.getProductManager()
					.getOneproducts_sort_price_down(id, startPoint, count);

		case 3:// 销量升序

			return ProductManager.getProductManager()
					.getOneproducts_sort_sale_up(id, startPoint, count);
		case 4:// 销量降序

			return ProductManager.getProductManager()
					.getOneproducts_sort_sale_down(id, startPoint, count);
		default:
			return ProductManager.getProductManager()
					.getProductListByPro_category_id(id, startPoint, count);
		}

	}

	/***
	 * 获取二级目录下的商品 根据不同的排序方式
	 * 
	 * @param key
	 * @param id
	 * @param startPoint
	 * @param count
	 * @return
	 */
	private List<Product> getTwoProductsSort(int key, int id, int startPoint,
			int count) {

		switch (key) {
		case 0:// 二级商品默认查询
			return ProductManager.getProductManager()
					.getProductListByPro_Type_id(id, startPoint, count);
		case 1: // 价格升序
			return ProductManager.getProductManager()
					.getTwoproducts_sort_price_up(id, startPoint, count);

		case 2:// 價格降序
			return ProductManager.getProductManager()
					.getTwoproducts_sort_price_down(id, startPoint, count);

		case 3:// 销量升序

			return ProductManager.getProductManager()
					.getTwoproducts_sort_sale_up(id, startPoint, count);
		case 4:// 销量降序

			return ProductManager.getProductManager()
					.getTwoproducts_sort_sale_down(id, startPoint, count);
		default:
			return ProductManager.getProductManager()
					.getProductListByPro_Type_id(id, startPoint, count);
		}

	}

	/***
	 * 获取三级目录下的商品 根据不同的排序方式
	 * 
	 * @param key
	 * @param id
	 * @param startPoint
	 * @param count
	 * @return
	 */
	private List<Product> getThreeProductsSort(int key, int id, int startPoint,
			int count) {

		switch (key) {
		case 0:// 三级商品默认查询
			return ProductManager.getProductManager()
					.getProductListByPro_brand_id(id, startPoint, count);
		case 1: // 价格升序
			return ProductManager.getProductManager()
					.getThreeproducts_sort_price_up(id, startPoint, count);

		case 2:// 價格降序
			return ProductManager.getProductManager()
					.getThreeproducts_sort_price_down(id, startPoint, count);

		case 3:// 销量升序

			return ProductManager.getProductManager()
					.getThreeproducts_sort_sale_up(id, startPoint, count);
		case 4:// 销量降序

			return ProductManager.getProductManager()
					.getThreeproducts_sort_sale_down(id, startPoint, count);
		default:
			return ProductManager.getProductManager()
					.getProductListByPro_brand_id(id, startPoint, count);
		}

	}

	/***
	 * 获取模糊搜索的商品 根据不同的排序方式
	 * 
	 * @param key
	 * @param id
	 * @param startPoint
	 * @param count
	 * @return
	 */
	private List<Product> getLikeProductsSort(int key, String str,
			int startPoint, int count) {

		switch (key) {
		case 0:// 三级商品默认查询
			return ProductManager.getProductManager().getProductListBylike(str,
					startPoint, count);
		case 1: // 价格升序
			return ProductManager.getProductManager()
					.getLikeproducts_sort_price_up(str, startPoint, count);

		case 2:// 價格降序
			return ProductManager.getProductManager()
					.getLikeproducts_sort_price_down(str, startPoint, count);

		case 3:// 销量升序

			return ProductManager.getProductManager()
					.getLikeproducts_sort_sale_up(str, startPoint, count);
		case 4:// 销量降序

			return ProductManager.getProductManager()
					.getLikeproducts_sort_sale_down(str, startPoint, count);
		default:
			return ProductManager.getProductManager().getProductListBylike(str,
					startPoint, count);
		}

	}

}
