package day_21;

import common.ReadFile;
import day_21.module.Case;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.floor;

public class Part2 {

    private static final int DEPTH = 500;

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("tmp.txt");
//            input = ReadFile.read("day_21.txt");
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
            for (Case current_case : tmp) {
                if (grid[nfmod(current_case.x() + 1, grid.length)][nfmod(current_case.y(), grid[0].length)] != '#')
                    current_cases.add(new Case(current_case.x() + 1, current_case.y()));
                if (grid[nfmod(current_case.x() - 1, grid.length)][nfmod(current_case.y(), grid[0].length)] != '#')
                    current_cases.add(new Case(current_case.x() - 1, current_case.y()));
                if (grid[nfmod(current_case.x(), grid.length)][nfmod(current_case.y() + 1, grid[0].length)] != '#')
                    current_cases.add(new Case(current_case.x(), current_case.y() + 1));
                if (grid[nfmod(current_case.x(), grid.length)][nfmod(current_case.y() - 1, grid[0].length)] != '#')
                    current_cases.add(new Case(current_case.x(), current_case.y() - 1));
            }
        }
        System.out.println(current_cases.size());
    }

    static int nfmod(float a, float b)
    {
        return (int) (a - b * floor(a / b));
    }
}
