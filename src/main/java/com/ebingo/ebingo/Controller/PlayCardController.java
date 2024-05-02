package com.ebingo.ebingo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebingo.ebingo.Service.PlayCardService;
@RestController
@CrossOrigin
public class PlayCardController {
    @Autowired
    private final PlayCardService playCardService;

    public PlayCardController(PlayCardService playCardService) {
        this.playCardService = playCardService;
    }

    @GetMapping("getcard/")
    public String getNewCard(@RequestParam String bcode) {
        return playCardService.generateCard(bcode);
    }

    @GetMapping("check-win/")
    public String checkWin(@RequestParam String playcard_token) {
        return Integer.toString(playCardService.checkWin(playcard_token));
    }
}
