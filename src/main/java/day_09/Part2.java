package day_09;

import common.ReadFile;
import day_09.module.Oasis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_09.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        List<Oasis> oasis = new ArrayList<>();
        for (String line : input)
            oasis.add(new Oasis(line.split(" ")));

        oasis.forEach(Oasis::compute);
        System.out.println(oasis.stream().mapToInt(Oasis::predict_first).sum());
    }
}
