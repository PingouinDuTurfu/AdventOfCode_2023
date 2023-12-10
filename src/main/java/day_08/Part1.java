package day_08;

import common.ReadFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static final String REGEX = "([A-Z]+) = \\(([A-Z]+), ([A-Z]+)\\)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_08.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        List<Character> path = input[0].chars().mapToObj(c -> (char) c).toList();
        Map<String, String[]> nodes = new HashMap<>();
        for (int i = 2; i < input.length; i++) {
            Matcher matcher = PATTERN.matcher(input[i]);
            if(matcher.find())
                nodes.put(matcher.group(1), new String[]{matcher.group(2), matcher.group(3)});
            else {
                System.out.println("Error");
                return;
            }
        }

        String current_node = "AAA";
        int i = 0;
        while (!current_node.equals("ZZZ")) {
            String[] next_nodes = nodes.get(current_node);
            int index = path.get(i % path.size()) == 'L' ? 0 : 1;
            current_node = next_nodes[index];
            i++;
        }
        System.out.println(i);
    }
}
