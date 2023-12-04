package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {

    public static void main(String[] args) {
        String input1 = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" +
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" +
                "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" +
                "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" +
                "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" +
                "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11";

        List<String> lineSplit = Arrays.stream(input1.split("\n")) // Separate lines
                .map(s -> s.substring(s.indexOf(":") + 2 )).toList();    // Remove "Card xx:"
        List<List<String>> win = lineSplit.stream().map(s ->
                Arrays.stream(s.substring(0, s.indexOf(" | ")).split(" ")).collect(Collectors.toList())
        ).toList();
        List<List<String>> draw = lineSplit.stream().map(s ->
                Arrays.stream(s.substring(s.indexOf("|") + 2).split(" ")).collect(Collectors.toList())
        ).toList();

        // PART 1
        long res1 = 0;
        for(int i=0; i<win.size(); i++){
            List<String> game = win.get(i);
            long found = draw.get(i).stream().filter(nb -> !nb.isEmpty() && game.contains(nb)).count();
            res1 += found > 0 ? Math.pow(2.0, found - 1) : 0;
        }


        // PART 2
        int res2 = recursive(0, win.size(), win, draw) - 1;

        System.out.println(res1);
        System.out.println(res2);
    }

    static int recursive(int startIndex, int endIndex, List<List<String>> win, List<List<String>> draw){
        int res = 1;
        for(int i=startIndex; i<endIndex; i++){
            List<String> game = win.get(i);
            long found = draw.get(i).stream().filter(nb -> !nb.isEmpty() && game.contains(nb)).count();
            res += recursive(i+1, (int) (i+1+found), win, draw);
        }
        return res;
    }

}
