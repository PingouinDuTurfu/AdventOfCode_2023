package day_09.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Oasis {

    List<List<Integer>> values = new ArrayList<>();

    public Oasis(String[] input) {
        List<Integer> row = Arrays.stream(input).map(Integer::parseInt).toList();
        values.add(row);
    }

    public void compute() {
        List<Integer> newRow;
        int i = 0;
        do {
            newRow = new ArrayList<>();
            List<Integer> row = values.get(i);
            for (int j = 0; j < row.size() - 1; j++)
                newRow.add(row.get(j + 1) - row.get(j));
            values.add(newRow);
            i++;
        } while (newRow.stream().anyMatch(v -> v != 0));
    }

    public int predict_last() {
        int result = values.getLast().getLast();
        for (int i = values.size() - 2; i >= 0; i--) {
            result = values.get(i).getLast() + result;
        }
        return result;
    }

    public int predict_first() {
        int result = values.getLast().getFirst();
        for (int i = values.size() - 2; i >= 0; i--) {
            result = values.get(i).getFirst() - result;
        }
        return result;
    }
}
