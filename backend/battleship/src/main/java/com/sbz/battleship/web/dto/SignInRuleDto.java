package com.sbz.battleship.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignInRuleDto {

	private Integer n;
	private Integer denieTime;
	private Integer failTime;
}
