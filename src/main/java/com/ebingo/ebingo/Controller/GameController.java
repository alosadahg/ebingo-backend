package com.ebingo.ebingo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebingo.ebingo.Model.Game;
import com.ebingo.ebingo.Service.GameService;

@Controller
@CrossOrigin
public class GameController {
    @Autowired
    private final GameService gameService;
    private String bcode;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/new-game")
    public String createNewGame() {
        bcode = gameService.createNewGame().getGameCode();
        return "redirect:/?bcode=" + bcode; 
    }

    @GetMapping("/")
    public String getDashboard(@RequestParam String bcode, @RequestParam(required = false) String dr, Model model) {
        this.bcode = bcode;
        Game gameFound = gameService.findGame(bcode);
        if(gameFound != null) {
            model.addAttribute("gameFound",gameFound);
        } else {
            model.addAttribute("gameFound","Game not found");
        }
        if(dr != null && dr.equals("1")) {
            model.addAttribute("dr", "1");
            int ball = gameService.getNextBall(bcode);
            if(ball < 1) {
                model.addAttribute("nextBall", "Game finished");
            } else {
                model.addAttribute("nextBall", ball);
            }
        }
        return "dashboard";
    }

    @GetMapping("/next-ball")
    public String getNextBall() {
        return "redirect:/?bcode=" + bcode + "&dr=1";
    }
}
