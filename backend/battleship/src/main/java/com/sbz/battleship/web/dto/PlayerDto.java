package com.sbz.battleship.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerDto {

    private UUID id;
    private String email;
    private String nick;

}
