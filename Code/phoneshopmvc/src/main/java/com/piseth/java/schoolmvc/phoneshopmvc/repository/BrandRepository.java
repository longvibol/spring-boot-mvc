package com.piseth.java.schoolmvc.phoneshopmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piseth.java.schoolmvc.phoneshopmvc.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{

}
