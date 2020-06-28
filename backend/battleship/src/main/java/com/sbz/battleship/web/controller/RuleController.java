package com.sbz.battleship.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbz.battleship.domain.exception.InternalServerError;
import com.sbz.battleship.service.RuleService;
import com.sbz.battleship.web.dto.AfkRuleDto;
import com.sbz.battleship.web.dto.SignInRuleDto;

@RestController
@RequestMapping(value = "api/rules")
public class RuleController {
	
	private final RuleService ruleService;
	
	
	public RuleController(RuleService ruleService) {
		this.ruleService = ruleService;
	}
	
    @RequestMapping(
            path= "/afk",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createAfk(
            @RequestBody AfkRuleDto request
    ) throws InternalServerError {
        this.ruleService.createAfk(request);
        return ResponseEntity.ok().build(); 
    }
    
    @RequestMapping(
            path= "/signin",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createSignIn(
            @RequestBody SignInRuleDto request
    ) throws InternalServerError {
        this.ruleService.createSignIn(request);
        return ResponseEntity.ok().build();
    }

}
