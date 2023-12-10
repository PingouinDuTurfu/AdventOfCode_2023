package day_08;

import common.ReadFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    private static final String REGEX = "([A-Z0-9]+) = \\(([A-Z0-9]+), ([A-Z0-9]+)\\)";
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

        List<String> current_nodes = nodes.keySet().stream().filter(s -> s.endsWith("A")).toList();
        int[] node_memory = new int[current_nodes.size()];
        int i = 0;
        while (Arrays.stream(node_memory).anyMatch(n -> n == 0)) {
            int index = path.get(i % path.size()) == 'L' ? 0 : 1;
            current_nodes = current_nodes.stream().map(s -> nodes.get(s)[index]).toList();
            for (int j = 0; j < current_nodes.size(); j++) {
                String cn = current_nodes.get(j);
                if(cn.endsWith("Z"))
                    node_memory[j] = i + 1;
            }
            i++;
        }
        System.out.println(Arrays.toString(node_memory));
        // Then put the tab in https://www.dcode.fr/restes-chinois with remainder = 0
        System.out.println(22289513667691L);
    }
}
