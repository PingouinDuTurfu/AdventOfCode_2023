package day_05;

import common.ReadFile;
import day_05.module.Seed;
import day_05.module.Structure;
import day_05.module.StructurePart;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class Part2 {

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
        List<Double> seedsValues = Arrays.stream(input[0].split(":")[1].split(" ")).filter(s -> !s.isEmpty()).map(Double::parseDouble).toList();
        List<Seed> seeds = new ArrayList<>();
        for (int i = 0; i < seedsValues.size(); i+=2) {
            seeds.add(new Seed(seedsValues.get(i), seedsValues.get(i) + seedsValues.get(i + 1)));
        }

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
            double range = Double.parseDouble(split[2]) - 1;
            CATEGORIES_MAP.get(current_category).addPart(new StructurePart(source_start, source_start + range, destination_start, range));
        }

        for (String category : CATEGORIES_LIST) {
            seeds = seedCircle(seeds, category);
        }

        seeds.sort(Comparator.comparingDouble(Seed::getStart));
        System.out.println(BigDecimal.valueOf(seeds.get(0).getStart()).toPlainString());
    }

    private static List<Seed> seedCircle(List<Seed> seeds, String category) {
        List<Seed> results_seeds = new ArrayList<>();
        for (Seed seed : seeds) {
            List<Seed> seeds_remaining = new ArrayList<>(){{
                add(seed);
            }};
            for (StructurePart structurePart : CATEGORIES_MAP.get(category).getSubParts()) {
                double seed_start = seed.getStart();
                double seed_end = seed.getEnd();
                double source_start = structurePart.getSource_start();
                double source_end = structurePart.getSource_end();
                double destination_start = structurePart.getDestination_start();
                double delta = destination_start - source_start;

                if(source_start <= seed_start && seed_end <= source_end) {
                    results_seeds.add(new Seed(seed_start + delta, seed_end + delta));
                    seeds_remaining.remove(seed);
                }
                if(seed_start < source_start && source_end <= seed_end && source_start < source_end) {
                    results_seeds.add(new Seed(source_start + delta, source_end + delta));
                    seeds_remaining.remove(seed);
                }
                if(source_start < seed_start && source_end  <= seed_end && seed_start < source_end) {
                    results_seeds.add(new Seed(seed_start + delta, source_end + delta));
                    seeds_remaining.remove(seed);
                }
                if(seed_start < source_start && seed_end <= source_end && source_start < seed_end) {
                    results_seeds.add(new Seed(source_start + delta, seed_end + delta));
                    seeds_remaining.remove(seed);
                }
            }
            results_seeds.addAll(seeds_remaining);
        }
        return results_seeds;
    }
}
