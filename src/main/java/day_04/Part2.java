package day_04;

import common.ReadFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_04.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        int result = 0;
        int[] copies = new int[input.length];
        Arrays.fill(copies, 1);
        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            String[] split = line.split(":");
            String[] cards_split = split[1].split("\\|");
            List<Integer> winnings_numbers = Arrays.stream(cards_split[0].split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
            List<Integer> numbers = Arrays.stream(cards_split[1].split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
            int index = (int) numbers.stream().filter(winnings_numbers::contains).count();
            for (int j = i+1; j < i + index + 1; j++)
                if(j < input.length)
                    copies[j] = copies[j] + copies[i];
            result += copies[i];
        }
        System.out.println(result);
    }
}
