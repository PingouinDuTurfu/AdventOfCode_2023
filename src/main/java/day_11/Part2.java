package day_11;

import common.ReadFile;

import java.io.IOException;

public class Part2 {

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("tmp.txt");
//            input = ReadFile.read("day_11.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
