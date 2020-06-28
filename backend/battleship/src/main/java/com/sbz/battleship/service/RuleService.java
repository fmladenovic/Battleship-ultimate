package com.sbz.battleship.service;

import com.sbz.battleship.domain.exception.InternalServerError;
import com.sbz.battleship.web.dto.AfkRuleDto;
import com.sbz.battleship.web.dto.SignInRuleDto;

public interface RuleService {
	
    
    public void createAfk(AfkRuleDto afkRule) throws InternalServerError;
    
    public void createSignIn(SignInRuleDto signIn) throws InternalServerError;

}
