package day_06;

import common.ReadFile;
import day_06.module.Race;

import java.io.IOException;
import java.util.Arrays;

public class Part1 {

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_06.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        String[] times = Arrays.stream(input[0].split(":")[1].split(" ")).filter(s -> !s.isEmpty()).toArray(String[]::new);
        String[] records = Arrays.stream(input[1].split(":")[1].split(" ")).filter(s -> !s.isEmpty()).toArray(String[]::new);
        int result = 1;
        for (int i = 0; i < times.length; i++)
            result *= new Race(Integer.parseInt(times[i]), Integer.parseInt(records[i])).compute();
        System.out.println(result);
    }
}
