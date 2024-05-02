package com.ebingo.ebingo.Service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebingo.ebingo.Model.Game;
import com.ebingo.ebingo.Repo.GameRepo;

@Service
public class GameService {
    @Autowired
    private GameRepo gameRepo;
    
    public Game createNewGame() {
        Game g = new Game();
        gameRepo.save(g);
        return gameRepo.findById(g.getGameCode()).get();
    }

    public Game findGame(String bcode) {
        return gameRepo.findById(bcode).orElse(null);
    }

    public int getNextBall(String bcode) {
        Random random = new Random();
        boolean exists = false;
        while (!exists) {
            int ball = random.nextInt(75) + 1;
            Game game = findGame(bcode);
            List<Integer> rolledIntegers = game.getRolledNumbers();
            if (!rolledIntegers.isEmpty()) {
                if (rolledIntegers.size() == 75) {
                    return -1;
                }
            }
            if (!rolledIntegers.contains(ball)) {
                rolledIntegers.add(ball);
                game.setRolledNumbers(rolledIntegers);
                gameRepo.save(game);
                exists = true;
                return ball;
            }
        }
        return 0;
    }
    
}
