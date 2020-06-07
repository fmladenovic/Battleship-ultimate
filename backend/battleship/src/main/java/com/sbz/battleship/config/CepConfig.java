package com.sbz.battleship.config;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CepConfig {
	
	private final KieContainer kieContainer;

	public CepConfig(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	@Bean
	public KieSession signInSession() {
		return this.kieContainer.newKieSession("cepSession");
	}
	
	
	
}
