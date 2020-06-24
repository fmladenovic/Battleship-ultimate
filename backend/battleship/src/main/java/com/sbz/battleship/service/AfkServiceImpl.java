package com.sbz.battleship.service;

import java.util.UUID;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.sbz.battleship.web.dto.AfkDto;

@Service
public class AfkServiceImpl implements AfkService {
	
	
    private final SimpMessagingTemplate template;

    
    public AfkServiceImpl(SimpMessagingTemplate template) {
    	this.template = template;
    }
    
    
    
    public void warnFrontend(UUID gameId) {
        this.template.convertAndSend("/topic/notice", new AfkDto(gameId, false));
    }

    public void changeFrontend(UUID gameId) {
        this.template.convertAndSend("/topic/notice", new AfkDto(gameId, true));
    }
}
