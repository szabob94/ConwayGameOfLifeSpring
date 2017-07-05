package hu.bence.springapplications.service;

import hu.bence.springapplications.service.domain.GameState;

public interface GameStateService {

    GameState calculateNextGameState(GameState gameState);
}
