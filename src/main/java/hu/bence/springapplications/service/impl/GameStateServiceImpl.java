package hu.bence.springapplications.service.impl;

import hu.bence.springapplications.service.GameStateService;
import hu.bence.springapplications.service.domain.Cell;
import hu.bence.springapplications.service.domain.GameState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameStateServiceImpl implements GameStateService {
    @Override
    public GameState calculateNextGameState(GameState gameState) {
        List<Cell> newCells = new ArrayList<>();

        int edgeLength = 100;
        boolean[][] gameStateArray = new boolean[edgeLength][edgeLength];
        boolean[][] revivedCells = new boolean[edgeLength][edgeLength];

        for (Cell cell : gameState.getCellList()){
            try {
                gameStateArray[cell.getX()][cell.getY()] = true;
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        for (Cell cell : gameState.getCellList()){
            if (shouldSurvive(cell, gameStateArray)) {
                newCells.add(cell);
            }
            reviveNeighbors(cell, gameStateArray, revivedCells, newCells);
        }


        GameState newStructure = new GameState();
        newStructure.setCellList(newCells);
        return newStructure;
    }

    private void reviveNeighbors(Cell cell, boolean[][] gameStateArray, boolean[][] revivedCells,
                                 List<Cell> newElements) {
        int x = cell.getX();
        int y = cell.getY();
        int edgeLength = gameStateArray.length;
        assert x >= 0 && x < edgeLength && y >= 0 && y < edgeLength;

        reviveElement(x - 1, y - 1, gameStateArray, revivedCells, newElements);
        reviveElement(x - 1, y, gameStateArray, revivedCells, newElements);
        reviveElement(x - 1, y + 1, gameStateArray, revivedCells, newElements);
        reviveElement(x, y - 1, gameStateArray, revivedCells, newElements);
        reviveElement(x, y + 1, gameStateArray, revivedCells, newElements);
        reviveElement(x + 1, y - 1, gameStateArray, revivedCells, newElements);
        reviveElement(x + 1, y, gameStateArray, revivedCells, newElements);
        reviveElement(x + 1, y + 1, gameStateArray, revivedCells, newElements);
    }

    private void reviveElement(int x, int y, boolean[][] gameStateArray, boolean[][] revivedCells,
                               List<Cell> newElements) {
        int edgeLength = gameStateArray.length;
        if (x >= 0 && x < edgeLength && y >= 0 && y < edgeLength) {
            if (!revivedCells[x][y]) {
                if (3 == neighborsNumber(x, y, gameStateArray)) {
                    revivedCells[x][y] = true;
                    newElements.add(new Cell(x, y));
                }
            }
        }
    }

    private int neighborsNumber(int x, int y, boolean[][] gameStateArray) {

        int edgeLength = 100;

        int neighborsNumber = 0;

        if (x > 0) {
            if (gameStateArray[x - 1][y]) {
                neighborsNumber++;
            }
            if (y > 0 && gameStateArray[x - 1][y - 1]) {
                neighborsNumber++;
            }
            if (y < edgeLength - 1 && gameStateArray[x - 1][y + 1]) {
                neighborsNumber++;
            }
        }
        if (y > 0) {
            if (gameStateArray[x][y - 1]) {
                neighborsNumber++;
            }
        }
        if (y < edgeLength - 1) {
            if (gameStateArray[x][y + 1]) {
                neighborsNumber++;
            }
        }
        if (x < edgeLength - 1) {
            if (gameStateArray[x + 1][y]) {
                neighborsNumber++;
            }
            if (y > 0 && gameStateArray[x + 1][y - 1]) {
                neighborsNumber++;
            }
            if (y < edgeLength - 1 && gameStateArray[x + 1][y + 1]) {
                neighborsNumber++;
            }
        }
        return neighborsNumber;
    }

    private boolean shouldSurvive(Cell cell, boolean[][] gameStateArray) {
        int n = neighborsNumber(cell.getX(), cell.getY(), gameStateArray);
        if (2 == n || 3 == n) {
            return true;
        }
        return false;
    }
}
