package day_10;

import common.ReadFile;
import day_10.module.CaseV2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {
        String[] input;
        try {
//            input = ReadFile.read("tmp.txt");
            input = ReadFile.read("day_10.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        Character[][] map = new Character[input.length][input[0].length()];
        List<CaseV2> cases = new ArrayList<>();
        for (int i = 0; i < input.length; i++)
            map[i] = input[i].chars().mapToObj(c -> (char) c).toArray(Character[]::new);


        CaseV2 start = null;
        for (int x = 0; x < map.length; x++)
            for (int y = 0; y < map[x].length; y++)
                if (map[x][y] == 'S') {
                    start = new CaseV2(x, y, ' ', ' ');
                    break;
                }

        if(start == null)
            throw new RuntimeException("No start found");

        CaseV2 current_case;
        if(start.getX() + 1 < map.length)
            current_case = new CaseV2(start.getX() + 1, start.getY(), map[start.getX() + 1][start.getY()], 'S');
        else if(start.getX() - 1 >= 0)
            current_case = new CaseV2(start.getX() - 1, start.getY(), map[start.getX() - 1][start.getY()], 'N');
        else if(start.getY() + 1 < map[start.getX()].length)
            current_case = new CaseV2(start.getX(), start.getY() + 1, map[start.getX()][start.getY() + 1], 'E');
        else if(start.getY() - 1 >= 0)
            current_case = new CaseV2(start.getX(), start.getY() - 1, map[start.getX()][start.getY() - 1], 'W');
        else
            throw new RuntimeException("No end found");

        while(current_case.getX() != start.getX() || current_case.getY() != start.getY()) {
            cases.add(current_case);
            current_case = getaCase(map, current_case);
        }

        current_case.setValue(switch (cases.get(0).getPresvious_direction()) {
            case 'N' -> {
                switch (current_case.getPresvious_direction()) {
                    case 'N':
                    case 'S': yield '|';
                    case 'E': yield 'J';
                    case 'W': yield 'L';
                    default:
                        throw new IllegalStateException("Unexpected value: " + current_case.getPresvious_direction());
                }
            }
            case 'S' -> {
                switch (current_case.getPresvious_direction()) {
                    case 'S':
                    case 'N': yield '|';
                    case 'E': yield 'F';
                    case 'W': yield '7';
                    default:
                        throw new IllegalStateException("Unexpected value: " + current_case.getPresvious_direction());
                }
            }
            case 'E' -> {
                switch (current_case.getPresvious_direction()) {
                    case 'N': yield 'L';
                    case 'S': yield 'F';
                    case 'E':
                    case 'W': yield '-';
                    default:
                        throw new IllegalStateException("Unexpected value: " + current_case.getPresvious_direction());
                }
            }
            case 'W' -> {
                switch (current_case.getPresvious_direction()) {
                    case 'N': yield 'J';
                    case 'S': yield '7';
                    case 'W':
                    case 'E': yield '-';
                    default:
                        throw new IllegalStateException("Unexpected value: " + current_case.getPresvious_direction());
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + cases.get(0).getPresvious_direction());
        });

        cases.add(current_case);

        int result = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length ; y++) {
                int finalX = x;
                int finalY = y;
                if(cases.stream().anyMatch(c -> c.getX() == finalX && c.getY() == finalY))
                    continue;
                int walls = 0;
                boolean left = false;
                boolean right = false;
                for (int k = x; k < map.length; k++) {
                    int finalK = k;
                    CaseV2 current_wall = cases.stream().filter(c -> c.getX() == finalK && c.getY() == finalY).findFirst().orElse(null);
                    if(current_wall != null) {
                        switch (current_wall.getValue()) {
                            case '|': break;
                            case '-': walls++; break;
                            case 'F':
                            case 'L': {
                                if(left) {
                                    walls++;
                                    left = false;
                                } else
                                    right = true;
                            }
                            case 'J':
                            case '7': {
                                if(right) {
                                    walls++;
                                    right = false;
                                } else
                                    left = true;
                            }
                        }
                    }
                }
                if(walls % 2 == 1)
                    result++;
            }
        }
        System.out.println(result);
    }

    private static CaseV2 getaCase(Character[][] map, CaseV2 c) {
        return switch (c.getValue()) {
            case '|' -> {
                if (c.getPresvious_direction() == 'N')
                    yield new CaseV2(c.getX() - 1, c.getY(), map[c.getX() - 1][c.getY()], 'N');
                else
                    yield new CaseV2(c.getX() + 1, c.getY(), map[c.getX() + 1][c.getY()], 'S');
            }
            case '-' -> {
                if (c.getPresvious_direction() == 'E')
                    yield new CaseV2(c.getX(), c.getY() + 1, map[c.getX()][c.getY() + 1], 'E');
                else
                    yield new CaseV2(c.getX(), c.getY() - 1, map[c.getX()][c.getY() - 1], 'W');
            }
            case 'L' -> {
                if (c.getPresvious_direction() == 'W')
                    yield new CaseV2(c.getX() - 1, c.getY(), map[c.getX() - 1][c.getY()], 'N');
                else
                    yield new CaseV2(c.getX(), c.getY() + 1, map[c.getX()][c.getY() + 1], 'E');
            }
            case 'F' -> {
                if (c.getPresvious_direction() == 'W')
                    yield new CaseV2(c.getX() + 1, c.getY(), map[c.getX() + 1][c.getY()], 'S');
                else
                    yield new CaseV2(c.getX(), c.getY() + 1, map[c.getX()][c.getY() + 1], 'E');
            }
            case 'J' -> {
                if (c.getPresvious_direction() == 'E')
                    yield new CaseV2(c.getX() - 1, c.getY(), map[c.getX() - 1][c.getY()], 'N');
                else
                    yield new CaseV2(c.getX(), c.getY() - 1, map[c.getX()][c.getY() - 1], 'W');
            }
            case '7' -> {
                if (c.getPresvious_direction() == 'E')
                    yield new CaseV2(c.getX() + 1, c.getY(), map[c.getX() + 1][c.getY()], 'S');
                else
                    yield new CaseV2(c.getX(), c.getY() - 1, map[c.getX()][c.getY() - 1], 'W');
            }
            default -> throw new IllegalStateException("Unexpected value: " + c.getValue());
        };
    }
}
