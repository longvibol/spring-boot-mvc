package com.piseth.java.school.phoneshopenight.service;

import com.piseth.java.school.phoneshopenight.dto.SaleDTO;
import com.piseth.java.school.phoneshopenight.entity.Sale;

public interface SaleService {
	
	void sell(SaleDTO saleDto);
	
	Sale getById(Long saleId);
	void cancelSale(Long saleId);

}
