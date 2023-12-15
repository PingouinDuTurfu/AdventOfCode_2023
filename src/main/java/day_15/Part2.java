package day_15;

import common.ReadFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_15.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        Map<Integer, List<Map<String, Integer>>> boxes = new HashMap<>();
        String[] keys = input[0].split(",");
        for (String key: keys) {
            String label = key.split("[-=]")[0];
            int box_index = asciiAlgo(label);
            List<Map<String, Integer>> box = boxes.get(box_index);
            if(key.contains("-")) {
                if(box != null) {
                    box.stream().filter(box_map -> box_map.containsKey(label)).findFirst().ifPresent(box_map -> box_map.remove(label));
                }
            } else {
                int value = Integer.parseInt(key.split("=")[1]);
                Map<String, Integer> box_map = new HashMap<>();
                box_map.put(label, value);
                if(box == null) {
                    boxes.put(box_index, new ArrayList<>(List.of(box_map)));
                } else {
                    box.stream().filter(box_map1 -> box_map1.containsKey(label)).findFirst().map(box_map1 -> {
                        box_map1.put(label, value);
                        return box_map1;
                    }).orElseGet(() -> {
                        box.add(box_map);
                        return box_map;
                    });
                }
            }
        }
        int result = 0;
        for (Map.Entry<Integer, List<Map<String, Integer>>> entry: boxes.entrySet()) {
            int local_result = 0;
            int box_index = entry.getKey() + 1;
            int real_i = 0;
            List<Map<String, Integer>> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                Map<String, Integer> box_map = value.get(i);
                if (box_map.isEmpty()) {
                    continue;
                }
                real_i++;
                local_result += box_index * box_map.values().stream().mapToInt(Integer::intValue).sum() * real_i;
            }
            result += local_result;
        }
        System.out.println(result);
    }

    private static int asciiAlgo(String label){
        int[] ascii = label.chars().toArray();
        int local_result = 0;
        for (int i: ascii) {
            local_result += i;
            local_result *= 17;
            local_result = local_result % 256;
        }
        return local_result;
    }
}
