package com.piseth.java.school.phoneshopenight.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {
	
	private Long id;
	private String name;

}
