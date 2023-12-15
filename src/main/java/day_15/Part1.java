package day_15;

import common.ReadFile;

import java.io.IOException;
import java.util.Arrays;

public class Part1 {

    public static void main(String[] args) {
        String[] input;
        try {
//            input = ReadFile.read("tmp.txt");
            input = ReadFile.read("day_15.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        String[] keys = input[0].split(",");
        int result = 0;
        for (String key: keys) {
            int[] ascii = key.chars().toArray();
            int local_result = 0;
            for (int i: ascii) {
                local_result += i;
                local_result *= 17;
                local_result = local_result % 256;
            }
            result += local_result;
        }
        System.out.println(result);
    }
}
