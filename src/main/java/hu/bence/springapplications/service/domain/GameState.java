package hu.bence.springapplications.service.domain;

import java.util.List;

public class GameState {
    private List<Cell> cellList;

    public GameState() {
    }

    public GameState(List<Cell> cellList) {
        this.cellList = cellList;
    }

    public List<Cell> getCellList() {
        return cellList;
    }

    public void setCellList(List<Cell> cellList) {
        this.cellList = cellList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameState gameState = (GameState) o;

        return cellList != null ? cellList.equals(gameState.cellList) : gameState.cellList == null;
    }

    @Override
    public int hashCode() {
        return cellList != null ? cellList.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "cellList=" + cellList +
                '}';
    }
}
