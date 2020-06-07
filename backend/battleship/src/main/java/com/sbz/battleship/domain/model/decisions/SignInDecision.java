package com.sbz.battleship.domain.model.decisions;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignInDecision {
	
	private UUID playerId;
	private String playerNick;
	private Boolean success; // cant find player with that email, password
	
	
	private Boolean forbiden; // decision
	private String reason;
	
}
