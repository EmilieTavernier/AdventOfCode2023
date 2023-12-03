package com.company;

import java.util.*;

public class Day2 {

    public static void main(String[] args) {
        String input1 = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";

        Map<String, Integer> colorsCount = new HashMap<String, Integer>() {{
            put("red", 12);
            put("green", 13);
            put("blue", 14);
        }};

        // PART 1
        String[] gameSplit = input1.split("\n");
        int res1 = 0;
        for(int game=0; game<gameSplit.length; game++){
            String currGame = gameSplit[game];
            String[] drawSplit = currGame.substring(currGame.indexOf(":")).split(";");

            boolean ok = true;
            for(int draw=0; draw<drawSplit.length; draw++){
                String currDraw = drawSplit[draw];
                String[] cubeSplit = currDraw.split(",");

                for(int cube=0; cube<cubeSplit.length; cube++){
                    String currCube = cubeSplit[cube];
                    int nb = Integer.parseInt(currCube.replaceAll("[^0-9]", ""));
                    ok = colorsCount.entrySet().stream().noneMatch(entry ->
                        currCube.contains(entry.getKey()) && nb > entry.getValue()
                    );
                    if(!ok) break;
                }
                if(!ok) break;
            }
            if(ok) res1 += (game + 1);
        }

        // PART 2
        int res2 = 0;
        for(int game=0; game<gameSplit.length; game++){
            Map<String, Integer> maxPerGame = new HashMap<String, Integer>() {{
                put("red", 0);
                put("green", 0);
                put("blue", 0);
            }};
            String currGame = gameSplit[game];
            String[] split = currGame.substring(currGame.indexOf(":")).split("[;,]");

            maxPerGame.entrySet().forEach(entry -> entry.setValue(
                    Arrays.stream(split)
                            .filter(str -> str.contains(entry.getKey()))
                            .mapToInt(str -> Integer.parseInt(str.replaceAll("[^0-9]", "")))
                            .max().orElse(0)
                    )
            );
            res2 += maxPerGame.values().stream().reduce((a, b) -> a*b).get();
        }

        System.out.println(res1);
        System.out.println(res2);
    }
}
