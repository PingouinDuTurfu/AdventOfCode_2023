package day_03;

import common.ReadFile;
import day_03.module.GridCell;
import day_03.module.GridNumber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    private static final List<Character> digits = List.of('0','1','2','3','4','5','6','7','8','9');

    private static final Character GEAR = '*';

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_03.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        List<GridNumber> numbers = new ArrayList<>();
        List<GridCell> gears = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            boolean inNumber = false;
            GridNumber currentNumber = null;
            for (int j = 0; j < line.length(); j++) {
                if(line.charAt(j) == GEAR)
                    gears.add(new GridCell(i, j));

                if (digits.contains(line.charAt(j))) {
                    if (inNumber) {
                        currentNumber.add(new GridCell(i, j), line.charAt(j));
                    } else {
                        inNumber = true;
                        currentNumber = new GridNumber(new GridCell(i, j), line.charAt(j));
                    }
                } else {
                    if (inNumber) {
                        inNumber = false;
                        numbers.add(currentNumber);
                    }
                }
                if(j == line.length() - 1 && inNumber) {
                    inNumber = false;
                    numbers.add(currentNumber);
                }
            }
        }

        int result = 0;
        for (GridCell gear : gears) {
            List<GridNumber> collect = numbers.stream()
                    .filter(number -> number.getCells().stream()
                            .anyMatch(cell -> isNextToGear(gear.x(), gear.y(), cell.x(), cell.y())))
                    .toList();

            if(collect.size() == 2)
                result += collect.stream().mapToInt(GridNumber::getNumber).reduce(1, (a, b) -> a * b);

        }
        System.out.println(result);
    }

    private static boolean isNextToGear(int gearX, int gearY, int x, int y) {
        return isNextToGearUnit(gearX + 1, gearY, x, y)
                || isNextToGearUnit(gearX - 1, gearY, x, y)
                || isNextToGearUnit(gearX, gearY + 1, x, y)
                || isNextToGearUnit(gearX, gearY - 1, x, y)
                || isNextToGearUnit(gearX + 1, gearY + 1, x, y)
                || isNextToGearUnit(gearX - 1, gearY - 1, x, y)
                || isNextToGearUnit(gearX + 1, gearY - 1, x, y)
                || isNextToGearUnit(gearX - 1, gearY + 1, x, y);

    }

    private static boolean isNextToGearUnit(int gearX, int gearY, int x, int y) {
        return gearX == x && gearY == y;
    }
}
