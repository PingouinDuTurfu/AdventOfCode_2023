package day_10.module;

public class CaseV2 {
    private final int x;
    private final int y;
    Character value;
    Character presvious_direction;

    public CaseV2(int x, int y, Character value, Character presvious_direction) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.presvious_direction = presvious_direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Character getValue() {
        return value;
    }

    public Character getPresvious_direction() {
        return presvious_direction;
    }

    public void setValue(Character value) {
        this.value = value;
    }

    public void setPresvious_direction(Character presvious_direction) {
        this.presvious_direction = presvious_direction;
    }

    @Override
    public String toString() {
        return "CaseV2{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                ", presvious_direction=" + presvious_direction +
                '}';
    }
}
