package com.ebingo.ebingo.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebingo.ebingo.Model.Game;
import com.ebingo.ebingo.Model.Playcard;
import com.ebingo.ebingo.Repo.PlayCardRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Service
public class PlayCardService {
    @Autowired
    private PlayCardRepo playCardRepo;
    @Autowired
    private GameService gameService;
    private int[][] cardMatrix;
    private Game game;

    public String generateCard(String bcode) {
        if (gameService.findGame(bcode) == null) {
            return "0";
        }
        Playcard playcard = new Playcard(bcode);
        playCardRepo.save(playcard);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        try {
            return writer.writeValueAsString(playcard);
        } catch (Exception e) {
            return "Error generating card";
        }
    }

    public int checkWin(String playcard_token) {
        cardMatrix = new int[5][5];
        Playcard playcard = playCardRepo.findById(playcard_token).orElse(null);
        if (playcard != null) {
            if (playcard.getIsWinning() == 1) {
                return 1;
            }
            game = gameService.findGame(playcard.getGameCode());
            Map<String, List<Integer>> card = playcard.getCard();
            for (Map.Entry<String, List<Integer>> entry : card.entrySet()) {
                String key = entry.getKey();
                List<Integer> values = entry.getValue();
                switch (key) {
                    case "B":
                        fillColumn(values, cardMatrix, 0);
                        break;
                    case "I":
                        fillColumn(values, cardMatrix, 1);
                        break;
                    case "N":
                        fillColumn(values, cardMatrix, 2);
                        break;
                    case "G":
                        fillColumn(values, cardMatrix, 3);
                        break;
                    case "O":
                        fillColumn(values, cardMatrix, 4);
                        break;
                }
            }
            if(checkHorizontal() || checkVertical() ||checkDiagonal()) {
                playcard.setIsWinning(1);
                playCardRepo.save(playcard);
                return 1;
            }
            return 0;
        }
        return -1;
    }

    private void fillColumn(List<Integer> values, int[][] cardMatrix, int columnIndex) {
        List<Integer> rolled = game.getRolledNumbers();
        for (int i=0; i<5; i++) {
            if(rolled.contains(values.get(i))) {
                cardMatrix[i][columnIndex] = 1;
            }
        }
    }

    private boolean checkHorizontal() {
        for(int i=0; i<5; i++) {
            boolean isWinning = true;
            for(int j=0; j<5; j++) {
                if(cardMatrix[i][j] != 1) {
                    isWinning = false;
                    break;
                }
            }
            if(isWinning) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVertical() {
        for(int i=0; i<5; i++) {
            boolean isWinning = true;
            for(int j=0; j<5; j++) {
                if(cardMatrix[j][i] != 1) {
                    isWinning = false;
                    break;
                }
            }
            if(isWinning) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal() {
        boolean isWinning = true;
        for(int i=0; i<5; i++) {
            if(cardMatrix[i][i] != 1) {
                isWinning = false;
                break;
            }
        }
        if(isWinning) {
            return true;
        }
        isWinning = true;
        int j = 0;
        for(int i=4; i>=0; i--) {
            if(cardMatrix[i][j] != 1) {
                isWinning = false;
                break;
            }
            j++;
        }
        return isWinning;
    }
}
