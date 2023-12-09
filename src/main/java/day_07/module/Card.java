package day_07.module;

public class Card {

    private final Character value;
    private int repetitions;

    public Card(Character value, int repetitions) {
        this.value = value;
        this.repetitions = repetitions;
    }

    public Character getValue() {
        return value;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void addRepetitions(int repetitions) {
        this.repetitions += repetitions;
    }
}
