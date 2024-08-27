package com.piseth.java.school.phoneshopenight.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phoneshopenight.dto.ModelDTO;
import com.piseth.java.school.phoneshopenight.entity.Color;
import com.piseth.java.school.phoneshopenight.entity.Model;
import com.piseth.java.school.phoneshopenight.mapper.ModelEntityMapper;
import com.piseth.java.school.phoneshopenight.service.ColorService;
import com.piseth.java.school.phoneshopenight.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/models")
public class ModelController {
	
	private final ModelService modelService;
	private final ModelEntityMapper modelMapper;
	private final ColorService colorService;	
	

	@RolesAllowed("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO){
		Model model = modelMapper.toModel(modelDTO);
		model = modelService.save(model);
		return ResponseEntity.ok(modelMapper.toModelDTO(model));
	}
	
	@PostMapping("color")
	@PreAuthorize("hasAuthority('color:write')")
	public ResponseEntity<?> createColor(@RequestBody Color color) {	
		
		colorService.create(color);
		return ResponseEntity.ok(color);
	}
	
}
















