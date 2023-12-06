package day_04;

import common.ReadFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Part1 {

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_04.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        int result = 0;
        for (String line : input) {
            String[] split = line.split(":");
            String[] cards_split = split[1].split("\\|");
            List<Integer> winnings_numbers = Arrays.stream(cards_split[0].split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
            List<Integer> numbers = Arrays.stream(cards_split[1].split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
            result += (int) Math.pow(2, (int) numbers.stream().filter(winnings_numbers::contains).count() - 1);
        }
        System.out.println(result);
    }
}

