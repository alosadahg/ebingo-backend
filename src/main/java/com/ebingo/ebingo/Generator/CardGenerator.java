package com.ebingo.ebingo.Generator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CardGenerator {
    private static Map<String, List<Integer>> card = new HashMap<String,List<Integer>>();

    public static Map<String, List<Integer>> generateCard() {
        card.put("B", generateColumnValues(1, 15, 5));
        card.put("I", generateColumnValues(16, 30, 5));
        card.put("N", generateColumnValues(31, 45, 5));
        card.put("G", generateColumnValues(46, 60, 5));
        card.put("O", generateColumnValues(61, 75, 5));

        return card;
    }
    

    private static List<Integer> generateColumnValues(int min, int max, int count) {
        List<Integer> columnValues = new ArrayList<Integer>();
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = min; i <= max; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, new Random());
        columnValues.addAll(numbers.subList(0, count));
        return columnValues;
    }
}
