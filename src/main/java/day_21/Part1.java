package day_21;

import common.ReadFile;
import day_21.module.Case;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Part1 {

    private static final int DEPTH = 64;

    public static void main(String[] args) {
        String[] input;
        try {
//            input = ReadFile.read("tmp.txt");
            input = ReadFile.read("day_21.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        Set<Case> current_cases = new HashSet<>();

        char[][] grid = new char[input.length][input[0].length()];

        for (int i = 0; i < input.length; i++)
            for (int j = 0; j < input[0].length(); j++) {
                grid[i][j] = input[i].charAt(j);
                if(grid[i][j] == 'S')
                    current_cases.add(new Case(i, j));
            }

        for (int p = 0; p < DEPTH; p++) {
            Set<Case> tmp = new HashSet<>(current_cases);
            current_cases.clear();
            for (Case current_case : tmp)
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++) {
                        if (Math.abs(i) == Math.abs(j) || current_case.x() + i < 0 || current_case.x() + i >= grid.length || current_case.y() + j < 0 || current_case.y() + j >= grid[0].length)
                            continue;
                        Case new_case = new Case(current_case.x() + i, current_case.y() + j);
                        if (grid[new_case.x()][new_case.y()] == '#')
                            continue;
                        current_cases.add(new_case);
                    }
        }
        System.out.println(current_cases.size());
    }
}
