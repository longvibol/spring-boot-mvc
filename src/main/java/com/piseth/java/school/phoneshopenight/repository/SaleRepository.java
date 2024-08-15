package com.piseth.java.school.phoneshopenight.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phoneshopenight.entity.Sale;
import com.piseth.java.school.phoneshopenight.projection.ProductSold;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>
{
	
	List<ProductSold> findProductSold(LocalDate startDate, LocalDate endDate);

}
