package day_10.module;

public class Case {
    private final int x;
    private final int y;
    private final int c;
    Character p;

    public Case(int x, int y, Character p, int c) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.p = p;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int c() {
        return c;
    }

    public Character p() {
        return p;
    }
}
