package day_02;

import common.ReadFile;
import day_02.module.CubeSet;
import day_02.module.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_02.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        List<Game> games = new ArrayList<>();
        for (String line : input) {
            String[] split = line.split(":");
            int id = Integer.parseInt(split[0].replace("Game ", ""));
            List<CubeSet> sets = new ArrayList<>();
            for (String set : split[1].substring(1).split("; ")) {
                String[] colors = set.split(", ");
                int red = 0, green = 0, blue = 0;
                for (String color : colors) {
                    String[] color_split = color.split(" ");
                    switch (color_split[1]) {
                        case "red":
                            red = Integer.parseInt(color_split[0]);
                            break;
                        case "green":
                            green = Integer.parseInt(color_split[0]);
                            break;
                        case "blue":
                            blue = Integer.parseInt(color_split[0]);
                            break;
                    }
                }
                sets.add(new CubeSet(red, green, blue));
            }
            games.add(new Game(id, sets));
        }

        int result = 0;
        for (Game game : games)
            result += game.getMaxBlue() * game.getMaxGreen() * game.getMaxRed();
        System.out.println(result);
    }
}
