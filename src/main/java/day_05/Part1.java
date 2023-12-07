package day_05;

import common.ReadFile;
import day_05.module.Structure;
import day_05.module.StructurePart;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Part1 {

    private static final List<String> CATEGORIES_LIST = List.of(
            "seed-to-soil map:",
            "soil-to-fertilizer map:",
            "fertilizer-to-water map:",
            "water-to-light map:",
            "light-to-temperature map:",
            "temperature-to-humidity map:",
            "humidity-to-location map:"
    );

    private static final Map<String, Structure> CATEGORIES_MAP = Map.of(
            "seed-to-soil map:", new Structure(),
            "soil-to-fertilizer map:", new Structure(),
            "fertilizer-to-water map:", new Structure(),
            "water-to-light map:", new Structure(),
            "light-to-temperature map:", new Structure(),
            "temperature-to-humidity map:", new Structure(),
            "humidity-to-location map:", new Structure()
    );

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_05.txt");
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        String current_category = "";
        List<Double> seeds = Arrays.stream(input[0].split(":")[1].split(" ")).filter(s -> !s.isEmpty()).map(Double::parseDouble).toList();
        for (int i = 1; i < input.length; i++) {
            if(input[i].isEmpty())
                continue;
            String line = input[i];
            if(CATEGORIES_MAP.containsKey(line)) {
                current_category = line;
                continue;
            }
            String[] split = line.split(" ");
            double destination_start = Double.parseDouble(split[0]);
            double source_start = Double.parseDouble(split[1]);
            double range = Double.parseDouble(split[2]);
            CATEGORIES_MAP.get(current_category).addPart(new StructurePart(source_start, source_start + range - 1, destination_start, range));
        }

        List<Double> results = new ArrayList<>();
        for (Double seed : seeds) {
            Double result = seed;
            for (String cat : CATEGORIES_LIST) {
                result = CATEGORIES_MAP.get(cat).getValues(result);
            }
            results.add(result);
        }
        System.out.println(BigDecimal.valueOf(results.stream().min(Double::compareTo).get()));
    }
}
