package day_02.module;

import java.util.List;

public class Game {

    private final int id;

    public int getId() {
        return id;
    }

    private final List<CubeSet> sets;

    public Game(int id, List<CubeSet> sets) {
        this.id = id;
        this.sets = sets;
    }

    public int getMaxBlue() {
        return sets.stream().mapToInt(CubeSet::blue).max().orElse(0);
    }

    public int getMaxGreen() {
        return sets.stream().mapToInt(CubeSet::green).max().orElse(0);
    }

    public int getMaxRed() {
        return sets.stream().mapToInt(CubeSet::red).max().orElse(0);
    }
}
