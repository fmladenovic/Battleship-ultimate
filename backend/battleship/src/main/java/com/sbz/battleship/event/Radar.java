package com.sbz.battleship.event;

import java.util.Random;

import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class Radar {

	
	private final KieSession cepSession;
	// private final SocketService socketService;
	
	public Radar(
		KieSession cepSession // ,
		// SocketService socketService
	) {
		this.cepSession = cepSession;
		// this.socketService = socketService;

	}
	
    @Scheduled(fixedDelay = 1000)
    public void doScanOrNot() {
   
//    	Random random = new Random();
//    	int rnd = random.nextInt(4);
//    	if(rnd == 3) {
//        	this.cepSession.insert(new Scan());
//        	// send signal that radar is on
//    	}
    	
    	this.cepSession.fireAllRules();
    }
	
}
