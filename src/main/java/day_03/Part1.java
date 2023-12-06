package day_03;

import common.ReadFile;
import day_03.module.GridCell;
import day_03.module.GridNumber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

    private static final List<Character> digits = List.of('0','1','2','3','4','5','6','7','8','9');

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_03.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        Character[][] grid = new Character[input.length][input[0].length()];
        List<GridNumber> numbers = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            boolean inNumber = false;
            GridNumber currentNumber = null;
            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = line.charAt(j);
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
        for (GridNumber number : numbers) {
            boolean isNextToSymbol = false;
            for (GridCell cell : number.getCells()) {
                if (isNextToSymbol(grid, cell.x(), cell.y())) {
                    isNextToSymbol = true;
                    break;
                }
            }
            if (isNextToSymbol)
                result += number.getNumber();
        }
        System.out.println(result);
    }

    private static boolean isNextToSymbol(Character[][] grid, int x, int y) {
        return x > 0 && isNextToSymbolUnit(grid, x - 1, y)
                || x < grid.length - 1 && isNextToSymbolUnit(grid, x + 1, y)
                || y > 0 && isNextToSymbolUnit(grid, x, y - 1)
                || y < grid[0].length - 1 && isNextToSymbolUnit(grid, x, y + 1)
                || x > 0 && y > 0 && isNextToSymbolUnit(grid, x - 1, y - 1)
                || x > 0 && y < grid[0].length - 1 && isNextToSymbolUnit(grid, x - 1, y + 1)
                || x < grid.length - 1 && y > 0 && isNextToSymbolUnit(grid, x + 1, y - 1)
                || x < grid.length - 1 && y < grid[0].length - 1 && isNextToSymbolUnit(grid, x + 1, y + 1);
    }

    private static boolean isNextToSymbolUnit(Character[][] grid, int x, int y) {
        return grid[x][y] != '.' && !digits.contains(grid[x][y]);
    }
}
