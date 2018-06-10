package com.it.dao;

import java.util.List;

import com.it.domain.Address1;

/***
 * 地址表管理类
 * 
 * @author Administrator
 * 
 */
public class AddressManager extends BaseDAO {

	public AddressManager() {
		// TODO Auto-generated constructor stub
	}

	/*********************** 单例模式 *****************************/
	private static AddressManager addressManager = new AddressManager();

	public static AddressManager getAddressManager() {
		return addressManager;
	}

	/*********************** 单例模式 *****************************/
	private String sql;

	/****
	 * 添加一条地址记录
	 * 
	 * @param address
	 *            地址实体类
	 * @return
	 */
	public boolean addAddress(Address1 address) {
		sql = "insert into address value("+address.getAddress_id()+",'" + address.getUserid() + "','"
				+ address.getRe_name() + "','" + address.getPhone() + "','"
				+ address.getAddress_msg() + "')";

		return super.update(sql);
	}

	/****
	 * 通过userid查找该用户的所有地址 的集合
	 * 
	 * @param userid
	 *            用户ID
	 * @return
	 */
	public List<Address1> getUserAddressByUserId(long userid) {

		sql = "select * from address  where userid=" + userid;

		return super.query(sql, Address1.class);

	}

	/***
	 * 根据地址ID查找地址
	 * 
	 * @param id
	 * @return
	 */
	public Address1 getUserAddressById(String id) {

		sql = "select * from address  where (address_id='" + id + "')";

		return (Address1) super.get(sql, Address1.class);

	}

	/***
	 * 根据地址ID和用户ID 更新地址信息
	 * 
	 * @param id
	 * @param address
	 * @param userid
	 * @param phone
	 * @param re_name
	 * @return
	 */
	public boolean updateAddress(long id, String address_msg, long userid,
			String phone, String re_name) {
		sql = "UPDATE address set address_msg = '" + address_msg + "', phone = '"
				+ phone + "' , re_name = '" + re_name + "' WHERE ( address_id = " + id
				+ " and userid = " + userid + ")";
		return super.update(sql);

	}

	/***
	 * 根据地址ID和用户ID 删除地址记录
	 * 
	 * @param id
	 *            地址ID
	 * @param userid
	 *            用户ID
	 * @return
	 */
	public boolean deleteAddress(long id, long userid) {

		sql = "delete from address where( address_id =" + id + " and userid =" + userid
				+ " )";
		return super.update(sql);
	}

}
