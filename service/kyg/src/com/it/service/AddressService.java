package com.it.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.it.Util.FastjsonTools;
import com.it.configs.ConfigValues;
import com.it.dao.AddressManager;
import com.it.domain.Address1;
import com.it.domain.Cart;

/***
 * 用户地址服务类
 * 
 * @author Alex
 * 
 */
public class AddressService {

	public AddressService() {
		// TODO Auto-generated constructor stub
	}

	/******************** 单例模式 ****************************/
	private static AddressService addressService = new AddressService();

	public static AddressService getAddressService() {
		return addressService;
	}

	/******************** 单例模式 ****************************/

	
	
	
	//**********************封装数据*******************************///
	
	
	private String key_addresses="addresses";
	private String key_address="address";
	/*****
	 * 创建返回json
	 * @param produts  list集合
	 * @return
	 */
	private Map<String, Object> creatResultMes(List<Address1> addresses){
		  Map<String, Object> map=new HashMap<String, Object>();
				if (addresses.size()>0) {
					map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
					map.put(this.key_addresses,addresses);
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
	private Map<String, Object> creatResultMes(Address1 address){
		  Map<String, Object> map=new HashMap<String, Object>();
		
		  
		  if (address!= null) {
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
				 map.put(this.key_address,address);
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
	private Map<String, Object> creatResultMes(Boolean isuccess){
		  Map<String, Object> map=new HashMap<String, Object>();
		
		  
		  if (isuccess) {
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
			
			}else{
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_ERROR);
			}
		  
		  		return map;
				
	}
	
	
	
	//**********************封装数据*******************************///
	
	
	
	
	
	
	/***
	 * 根据用户ID查找用户相关的地址信息集合
	 * 
	 * @param userid
	 *            用户ID
	 * @return 地址集合json数据
	 */
	public String getAddressByuserid(long userid) {

		List<Address1> addresses = AddressManager.getAddressManager()
				.getUserAddressByUserId(userid);

		return FastjsonTools.CreategsonString(this.creatResultMes(addresses));
	}

	/***
	 * 添加一条新地址
	 * 
	 * @param userid
	 * @param re_name
	 * @param phone
	 * @param address
	 * @return
	 */
	public String AddAddress(long userid, String re_name, String phone,
			String address) {
		// 创建一个地址实体
		Address1 addresss1 = new Address1(new Date().getTime(),userid, re_name, phone, address);
		
	
		
		boolean flag = AddressManager.getAddressManager().addAddress(addresss1);
		if (flag) {
			Address1	addresss2 = AddressManager.getAddressManager().getUserAddressById(
					String.valueOf(addresss1.getAddress_id()));
			return FastjsonTools.CreategsonString(this.creatResultMes(addresss2));
		} else {
			return FastjsonTools.CreategsonString(null);
		}
	}

	/***
	 * 删除地址
	 * @param id 地址ID
	 * @param userid  用户ID
	 * @return
	 */
	public String deleteAddress(long id, long userid) {
		return FastjsonTools.CreategsonString(this.creatResultMes(AddressManager
				.getAddressManager().deleteAddress(id, userid)));
	}
	
	
/***
 * 更新地址
 * @param id
 * @param userid
 * @param re_name
 * @param phone
 * @param address
 * @return
 */
	public String UpdataAddress(long id, long userid, String re_name,
			String phone, String address) {
		
		
		boolean flag=AddressManager
		.getAddressManager().updateAddress(id, address, userid, phone,
				re_name);
		
		
		return FastjsonTools.CreategsonString(this.creatResultMes(flag));
	}

}
