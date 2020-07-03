package com.sbz.battleship.service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.shared.invoker.MavenInvocationException;
import org.drools.template.ObjectDataCompiler;
import org.springframework.stereotype.Service;

import com.sbz.battleship.domain.exception.InternalServerError;
import com.sbz.battleship.repository.RulesHandler;
import com.sbz.battleship.web.dto.StatusDto;

@Service
public class RuleServiceImpl implements RuleService{
	
	private final RulesHandler rulesHandler;
	
	
	public RuleServiceImpl( RulesHandler rulesHandler ) {
		this.rulesHandler = rulesHandler;
	}
	
    private String extractRuleForStatus(StatusDto statusDto) {

        Map<String, Object> data = new HashMap<>();
        
        data.put("golden", statusDto.getGolden());
        data.put("silver", statusDto.getSilver());
        data.put("bronze", statusDto.getBronze());



        String templateName = "status-template";
        ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();

        String ruleContent = objectDataCompiler.compile(
                Collections.singletonList(data),
                Thread.currentThread().getContextClassLoader().getResourceAsStream("templates/" + templateName + ".drt")
        );

        return ruleContent;
    }
    

    
    @Override
    public void createStatus(StatusDto statusDto) throws InternalServerError {
    	String rule = this.extractRuleForStatus(statusDto);
    	try {
			this.rulesHandler.evaluate(rule, "status");
		} catch (IOException | MavenInvocationException e) {
			e.printStackTrace();
			throw new InternalServerError(e.getMessage());
		}
    }
    

	
	
}
