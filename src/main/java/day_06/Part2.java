package day_06;

import common.ReadFile;
import day_06.module.Race;

import java.io.IOException;
import java.util.Arrays;

public class Part2 {

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_06.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        String time = Arrays.stream(input[0].split(":")[1].split(" ")).filter(s -> !s.isEmpty()).reduce((s, s2) -> s + s2).orElse("");
        String record = Arrays.stream(input[1].split(":")[1].split(" ")).filter(s -> !s.isEmpty()).reduce((s, s2) -> s + s2).orElse("");
        System.out.println(new Race(Double.parseDouble(time), Double.parseDouble(record)).compute());
    }
}
