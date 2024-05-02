package com.ebingo.ebingo.Model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    @Id
    @GeneratedValue(generator = "game-code")
    @org.hibernate.annotations.GenericGenerator(name = "game-code", strategy = "com.ebingo.ebingo.Generator.GameCodeGenerator")
    private String gameCode;

    @Builder.Default
    @ElementCollection
    private List<Integer> rolledNumbers = List.of();
}
