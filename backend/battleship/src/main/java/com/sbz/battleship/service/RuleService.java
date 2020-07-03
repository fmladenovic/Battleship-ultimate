package com.sbz.battleship.service;

import com.sbz.battleship.domain.exception.InternalServerError;
import com.sbz.battleship.web.dto.StatusDto;

public interface RuleService {
	
    
    public void createStatus(StatusDto satusDto) throws InternalServerError;
    

}
