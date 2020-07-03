package com.sbz.battleship.web.dto;

import java.util.UUID;

import com.sbz.battleship.domain.model.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerDto {

    private UUID id;
    private String email;
    private String nick;
    private Status status;

}
