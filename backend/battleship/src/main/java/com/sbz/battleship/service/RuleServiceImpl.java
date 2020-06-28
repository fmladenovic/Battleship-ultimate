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
import com.sbz.battleship.web.dto.AfkRuleDto;
import com.sbz.battleship.web.dto.SignInRuleDto;

@Service
public class RuleServiceImpl implements RuleService{
	
	private final RulesHandler rulesHandler;
	
	
	public RuleServiceImpl( RulesHandler rulesHandler ) {
		this.rulesHandler = rulesHandler;
	}
	
    private String extractRuleForAfk(AfkRuleDto afkRule) {

        Map<String, Object> data = new HashMap<>();
        
        data.put("playerRegular", afkRule.getPlayerRegular());
        data.put("playerExtended", afkRule.getPlayerExtended());


        String templateName = "move-classes";
        ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();

        String ruleContent = objectDataCompiler.compile(
                Collections.singletonList(data),
                Thread.currentThread().getContextClassLoader().getResourceAsStream("templates/" + templateName + ".drt")
        );

        return ruleContent;
    }
    
    private String extractRuleForSignIn(SignInRuleDto signIn) {

        Map<String, Object> data = new HashMap<>();
        
        data.put("n", signIn.getN());
        data.put("denieTime", signIn.getDenieTime());
        data.put("failTime", signIn.getFailTime());



        String templateName = "signIn";
        ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();

        String ruleContent = objectDataCompiler.compile(
                Collections.singletonList(data),
                Thread.currentThread().getContextClassLoader().getResourceAsStream("templates/" + templateName + ".drt")
        );

        return ruleContent;
    }
    
    @Override
    public void createAfk(AfkRuleDto afkRule) throws InternalServerError {
    	String rule = this.extractRuleForAfk(afkRule);
    	try {
			this.rulesHandler.evaluate(rule, "move classes");
		} catch (IOException | MavenInvocationException e) {
			e.printStackTrace();
			throw new InternalServerError(e.getMessage());
		}
    }
    
    @Override
    public void createSignIn(SignInRuleDto signIn) throws InternalServerError {
    	String rule = this.extractRuleForSignIn(signIn);
    	try {
			this.rulesHandler.evaluate(rule, "signIn");
		} catch (IOException | MavenInvocationException e) {
			e.printStackTrace();
			throw new InternalServerError(e.getMessage());
		}
    }

	
	
}
