package com.piseth.java.school.phoneshopenight.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;


@Data
public class SaleDTO {
	
	@NotEmpty
	private List<ProductSoldDTO> products;
	private LocalDateTime saleDate;
	
}
