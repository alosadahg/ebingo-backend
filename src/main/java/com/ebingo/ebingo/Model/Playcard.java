package com.ebingo.ebingo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;
import com.ebingo.ebingo.Generator.CardGenerator;

@Entity
@Table(name = "playcard")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Playcard {
    @Id
    @GeneratedValue(generator = "playcard_token")
    @org.hibernate.annotations.GenericGenerator(name = "playcard_token", strategy = "com.ebingo.ebingo.Generator.PlaycardTokenGenerator")
    private String playcardToken;
    private String gameCode;
    private Integer isWinning = 0; 
    @ElementCollection
    private Map<String, List<Integer>> card;

    public Playcard(String gameCode) {
        this.gameCode = gameCode;
        this.card = CardGenerator.generateCard();
    }
}
