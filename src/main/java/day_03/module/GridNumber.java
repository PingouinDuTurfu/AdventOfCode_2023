package day_03.module;

import java.util.ArrayList;
import java.util.List;

public class GridNumber {

    private final List<GridCell> cells;

    private int number;

    public GridNumber(GridCell cells, char number) {
        this.cells = new ArrayList<>();
        this.cells.add(cells);
        this.number = Integer.parseInt("" + number);
    }

    public List<GridCell> getCells() {
        return cells;
    }

    public int getNumber() {
        return number;
    }

    public void add(GridCell cell, char number) {
        cells.add(cell);
        this.number = Integer.parseInt("" + this.number + number);
    }

    @Override
    public String toString() {
        return "GridNumber{" +
                "cells=" + cells +
                ", number=" + number +
                '}';
    }
}
