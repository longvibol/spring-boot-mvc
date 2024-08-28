package com.piseth.java.school.phoneshopenight.entity;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


@MappedSuperclass
public abstract class AuditEntity {

	@CreatedDate
	private LocalDateTime dateCreate;
	
	@LastModifiedDate
	private LocalDateTime dateUpdate;
	
	@CreatedDate
	private String userCreate;
	
	@LastModifiedDate
	private String userUpdate;
	
}
