package com.it.test;



import java.util.List;

import org.junit.Test;






import com.it.dao.AddressManager;
import com.it.dao.CartManager;
import com.it.dao.ProductManager;

import com.it.domain.Address1;
import com.it.domain.Product;


public class HomeDaoTest {

	@Test
	public void testQuery_Home_Banners() {
		
     List<Address1> aa = AddressManager.getAddressManager().getUserAddressByUserId(4);
     System.out.println(aa.size());
     for (Address1 address : aa) {
		System.out.println(address.getAddress_msg());
	}
    }

}
