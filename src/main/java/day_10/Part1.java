package day_10;

import common.ReadFile;
import day_10.module.Case;

import java.io.IOException;

public class Part1 {

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_10.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        Character[][] map = new Character[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++)
            map[i] = input[i].chars().mapToObj(c -> (char) c).toArray(Character[]::new);


        Case start = null;
        for (int x = 0; x < map.length; x++)
            for (int y = 0; y < map[x].length; y++)
                if (map[x][y] == 'S') {
                    start = new Case(x, y, 'S', 0);
                    break;
                }

        if(start == null)
            throw new RuntimeException("No start found");

        Case c1 = null;
        Case c2 = null;

        if(start.x() + 1 < map.length && (map[start.x() + 1][start.y()] == '|' || map[start.x() + 1][start.y()] == 'L' || map[start.x() + 1][start.y()] == 'J'))
            c1 = new Case(start.x() + 1, start.y(), 'S', 1);
        if(start.x() - 1 >= 0 && (map[start.x() - 1][start.y()] == '|' || map[start.x() - 1][start.y()] == 'F' || map[start.x() - 1][start.y()] == '7'))
            if(c1 == null)
                c1 = new Case(start.x() - 1, start.y(), 'N', 1);
            else
                c2 = new Case(start.x() - 1, start.y(), 'N', 1);
        if(start.y() + 1 < map[start.x()].length && (map[start.x()][start.y() + 1] == '-' || map[start.x()][start.y() + 1] == '7' || map[start.x()][start.y() + 1] == 'J'))
            if(c1 == null)
                c1 = new Case(start.x(), start.y() + 1, 'E', 1);
            else
                c2 = new Case(start.x(), start.y() + 1, 'E', 1);
        if(start.y() - 1 >= 0 && (map[start.x()][start.y() - 1] == '-' || map[start.x()][start.y() - 1] == 'F' || map[start.x()][start.y() - 1] == 'L'))
            c2 = new Case(start.x(), start.y() - 1, 'W', 1);

        if (c1 == null || c2 == null)
            throw new RuntimeException("No end found");

        while(c1.x() != c2.x() || c1.y() != c2.y()) {
            c1 = getaCase(map, c1);
            if(c1.x() == c2.x() && c1.y() == c2.y())
                break;
            c2 = getaCase(map, c2);
        }

        System.out.println(Math.max(c1.c(), c2.c()));
    }

    private static Case getaCase(Character[][] map, Case c) {
        return switch (map[c.x()][c.y()]) {
            case '|' -> c.p() == 'N' ? new Case(c.x() - 1, c.y(), 'N', c.c() + 1) : new Case(c.x() + 1, c.y(), 'S', c.c() + 1);
            case '-' -> c.p() == 'E' ? new Case(c.x(), c.y() + 1,'E', c.c() + 1) : new Case(c.x(), c.y() - 1, 'W', c.c() + 1);
            case 'L' -> c.p() == 'W' ? new Case(c.x() - 1, c.y(),  'N', c.c() + 1) : new Case(c.x(), c.y() + 1, 'E', c.c() + 1);
            case 'F' -> c.p() == 'W' ? new Case(c.x() + 1, c.y(),  'S', c.c() + 1) : new Case(c.x(), c.y() + 1, 'E', c.c() + 1);
            case 'J' ->  c.p() == 'E' ? new Case(c.x() - 1, c.y(), 'N', c.c() + 1) : new Case(c.x(), c.y() - 1, 'W', c.c() + 1);
            case '7' -> c.p() == 'E' ? new Case(c.x() + 1, c.y(), 'S', c.c() + 1) : new Case(c.x(), c.y() - 1, 'W', c.c() + 1);
            default -> throw new RuntimeException("Unknown pipe");
        };
    }
}
