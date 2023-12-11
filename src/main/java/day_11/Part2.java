package day_11;

import common.ReadFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    private static final int EXPANSION = 1000000 - 1;

    public static void main(String[] args) {
        String[] input;
        try {
//            input = ReadFile.read("tmp.txt");
            input = ReadFile.read("day_11.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        Character[][] grid = new Character[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++)
            for (int j = 0; j < input[0].length(); j++)
                grid[i][j] = input[i].charAt(j);

        List<Integer> row_expansion = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            Character[] row = grid[i];
            boolean found = false;
            for (Character c : row) {
                if (c == '#') {
                    found = true;
                    break;
                }
            }
            if (!found)
                row_expansion.add(i);
        }

        List<Integer> column_expansion = new ArrayList<>();
        for (int i = 0; i < grid[0].length; i++) {
            boolean found = false;
            for (Character[] row : grid) {
                if (row[i] == '#') {
                    found = true;
                    break;
                }
            }
            if (!found)
                column_expansion.add(i);
        }

        double result = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '#') {
                    boolean first_line = true;
                    for (int k = i; k < grid.length; k++) {
                        for (int l = 0; l < grid[0].length; l++) {
                            if(first_line && k == i && l <= j)
                                continue;
                            first_line = false;
                            if(grid[k][l] == '#') {
                                int finalI = i;
                                int finalK = k;
                                int finalL = l;
                                int finalJ = j;
                                result += Math.abs(k - i) + Math.abs(l - j) + row_expansion.stream().filter(x -> (x < finalK && x >= finalI) || (x >= finalK && x < finalI)).count() * EXPANSION + column_expansion.stream().filter(x -> (x < finalL && x >= finalJ) || (x >= finalL && x < finalJ)).count() * EXPANSION;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(BigDecimal.valueOf(result).toPlainString());
    }
}
