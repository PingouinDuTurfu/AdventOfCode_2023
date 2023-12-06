package day_01;

import common.ReadFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Part1 {

    private static final List<Character> numbers = List.of('1','2','3','4','5','6','7','8','9');
    private static final Map<String, Integer> string_to_number = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9);

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_01.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        input = Arrays.stream(input).map(line -> {
            for (String key : string_to_number.keySet()) {
                if (line.contains(key))
                    return line.replace(key, string_to_number.get(key).toString());
            }
            return line;
        }).toArray(String[]::new);

        int result = 0;
        for (String line : input) {
            Character firstNumber = null;
            Character lastNumber = null;
            for (char c : line.toCharArray()) {
                if (!numbers.contains(c))
                    continue;
                lastNumber = c;
                if (firstNumber == null)
                    firstNumber = c;
            }
            result += Integer.parseInt(firstNumber + lastNumber.toString());
        }
        System.out.println(result);
    }
}
