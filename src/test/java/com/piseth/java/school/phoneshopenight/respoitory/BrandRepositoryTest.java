package com.piseth.java.school.phoneshopenight.respoitory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.piseth.java.school.phoneshopenight.entity.Brand;
import com.piseth.java.school.phoneshopenight.repository.BrandRepository;


@DataJpaTest
public class BrandRepositoryTest {
	
	@Autowired
	private BrandRepository brandRepository;
	private List<Brand> byNameLike;
	
	@Test
	public void testFindByNameLike() {
		
		//Given
		
		Brand brand = new Brand();
		brand.setName("Apple");	
		
		
		Brand brand2 = new Brand();
		brand2.setName("Samsung");
		
		
		brandRepository.save(brand);
		brandRepository.save(brand2);
		
		// When 
		
		List<Brand> brands = brandRepository.findByNameLike("%A%");
		
		// then 
	
		assertEquals(1, brands.size());
		assertEquals("Apple", brands.get(0).getName()); // get inside list of brand index 0 = Apple  
		assertEquals(1, brands.get(0).getId()); // have id 1 
		
		// Test With findByNameContaining : 
		
		
		List<Brand> brandsContain = brandRepository.findByNameContaining("A");
		
		assertEquals(1, brandsContain.size());
		assertEquals("Apple", brandsContain.get(0).getName()); 
		assertEquals(1, brandsContain.get(0).getId());  
		
		
	}
	
}











