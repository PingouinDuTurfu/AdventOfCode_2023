package day_01;

import common.ReadFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Part2 {

    private static final List<Character> numbers = List.of('1','2','3','4','5','6','7','8','9');
    private static final List<String> string_number = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    private static final List<String> string_number_reverse = List.of("enin", "thgie", "neves", "xis", "evif", "ruof", "eerht", "owt", "eno");
    private static final Map<String, Integer> string_to_number = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
    );

    private static final Map<String, Integer> string_to_number_reverse = Map.of(
            "enin", 9,
            "thgie", 8,
            "neves", 7,
            "xis", 6,
            "evif", 5,
            "ruof", 4,
            "eerht", 3,
            "owt", 2,
            "eno", 1
    );


    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_01.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        int result = 0;
        for (String line : input) {
            String line_reversed = new StringBuilder(line).reverse().toString();
            line = computeLine(line, string_to_number, string_number);
            line_reversed = new StringBuilder(computeLine(line_reversed, string_to_number_reverse, string_number_reverse)).reverse().toString();

            Character firstNumber = null;
            Character lastNumber = null;
            for (char c : line.toCharArray()) {
                if (!numbers.contains(c))
                    continue;
                if (firstNumber == null)
                    firstNumber = c;
            }

            for (char c : line_reversed.toCharArray()) {
                if (!numbers.contains(c))
                    continue;
                lastNumber = c;
            }
            result += Integer.parseInt(firstNumber + lastNumber.toString());
        }
        System.out.println(result);
    }

    private static String replacePartOfString(String s, int start, int end, int number) {
        System.out.println("replacePartOfString " + s + " " + start + " " + end + " " + number);
        return s.substring(0, start) + number + s.substring(end);
    }

    private static String computeLine(String line, Map<String, Integer> string_to_number, List<String> string_number) {
        char[] chars_line = line.toCharArray();
        for (int i = 0; i < chars_line.length; i++) {
            for(String number : string_number) {
                char[] chars_number = number.toCharArray();
                int j;
                for (j = 0; j < chars_number.length; j++)
                    if(i+j >= chars_line.length || chars_number[j] != chars_line[i+j])
                        break;
                if(j == chars_number.length)
                    line = line.replace(number, string_to_number.get(number).toString());
            }
        }
        return line;
    }
}
