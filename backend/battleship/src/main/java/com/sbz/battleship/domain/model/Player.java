package com.sbz.battleship.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "players")
public class Player {

    @Id
    private UUID id;

    private String email;
    private String nick;
    private String password;

    @DBRef(lazy = true)
    private List<Game> games;
}
