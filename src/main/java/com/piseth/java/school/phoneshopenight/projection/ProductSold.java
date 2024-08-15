package com.piseth.java.school.phoneshopenight.projection;

import java.math.BigDecimal;

public interface ProductSold {
	
//	productid, productName, unit, totalAmout
	
	Long getProductId();
	String getProductName();
	Integer getUnit();
	BigDecimal getTotalAmout();


}
