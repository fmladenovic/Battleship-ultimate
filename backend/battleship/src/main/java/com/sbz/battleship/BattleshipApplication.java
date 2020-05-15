package com.sbz.battleship;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BattleshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(BattleshipApplication.class, args);
    }

    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("com.sbz", "resoner", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(1000);

//        KieSession kieSession = kContainer.newKieSession("session");
//        kieSession.addEventListener(new DebugAgendaEventListener());
//        kieSession.setGlobal("triggeredAlarmService", triggeredAlarmService);
        return kContainer;
    }
}
