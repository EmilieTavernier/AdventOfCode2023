package com.company;

import java.util.*;

public class Day1 {

    public static void main(String[] args) {
        String input1 = "1abc2\n" +
                "pqr3stu8vwx\n" +
                "a1b2c3d4e5f\n" +
                "treb7uchet";

        int res1 = Arrays.stream(input1.split("\n"))
                .map(s -> s.replaceAll("[^0-9]", ""))
                .filter(s -> s.length() != 0)
                .mapToInt(str -> Integer.parseInt("" + str.charAt(0) + str.charAt(str.length() - 1)))
                .sum();;

        String input2 = "two1nine\n" +
                "eightwothree\n" +
                "abcone2threexyz\n" +
                "xtwone3four\n" +
                "4nineeightseven2\n" +
                "zoneight234\n" +
                "7pqrstsixteen";

        String[] txt = {"1", "2", "3", "4", "5", "6", "7", "8", "9",
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] split = input2.split("\n");

        int res2 = 0;
        Optional<String> first = Optional.empty();
        Optional<String> last = Optional.empty();
        for(int i=0; i<split.length; i++) {
            for(int j=0; j<split.length; j++){
                if(first.isEmpty()){
                    String str = split[i].substring(j);
                    first = Arrays.stream(txt).filter(str::startsWith).findFirst();
                }
                if(last.isEmpty()){
                    String str = split[i].substring(0, split[i].length() - j);
                    last = Arrays.stream(txt).filter(str::endsWith).findFirst();
                }
            }
            res2 += Integer.parseInt("" +
                    (Arrays.stream(txt).toList().indexOf(first.get()) % 9 + 1) +
                    (Arrays.stream(txt).toList().indexOf(last.get()) % 9 + 1)
            );
            first = Optional.empty();
            last = Optional.empty();
        }

        System.out.println(res1);
        System.out.println(res2);
    }
}
