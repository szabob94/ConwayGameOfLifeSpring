package hu.bence.springapplications.view.restcontroller;

import hu.bence.springapplications.service.GameStateService;
import hu.bence.springapplications.service.domain.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/json/gamemechanism")
public class GameMechanismController {

    @Autowired
    GameStateService gameStateService;

    @RequestMapping(method = RequestMethod.POST)
    public GameState calculateNextTurn(@RequestBody GameState gameState){
        GameState nextTurnState = gameStateService.calculateNextGameState(gameState);
        return nextTurnState;
    }

}
