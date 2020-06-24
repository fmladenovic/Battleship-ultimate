package com.sbz.battleship.web.dto;

import java.util.UUID;

public class AfkDto {
	
	private UUID gameId;
	private boolean status; // status false - warning, status true - switch
	
	
	public AfkDto() {}
	
	public AfkDto(UUID gameId, boolean status) {
		super();
		this.gameId = gameId;
		this.status = status;
	}
	public UUID getGameId() {
		return gameId;
	}
	public void setGameId(UUID gameId) {
		this.gameId = gameId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AfkDto [gameId=" + gameId + ", status=" + status + "]";
	}
	
	
	
	
	
	
}
