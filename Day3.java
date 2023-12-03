package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Day3 {

    public static void main(String[] args) {
        String input1 = "467..114..\n" +
                "...*......\n" +
                "..35..633.\n" +
                "......#...\n" +
                "617*......\n" +
                ".....+.58.\n" +
                "..592.....\n" +
                "......755.\n" +
                "...$.*....\n" +
                ".664.598..";

        ArrayList<String> split = new ArrayList(Arrays.asList(input1.split("\n")));
        split.add(0, split.get(0).replaceAll(".", "."));
        split.add(split.get(0));
        List<String> safeSplit = split.stream().map(s -> "." + s + ".").collect(Collectors.toList());

        // PART 1
        int res1 = 0;
        for(int i=1; i<safeSplit.size()-1; i++){
            String str_i = safeSplit.get(i);
            List<String> numbersList = Arrays.stream(str_i.split("[^0-9]+")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
            for(int j=0; j<numbersList.size(); j++){
                String nb = numbersList.get(j);
                int start = str_i.indexOf(nb)-1;
                int end = start + nb.length() + 2;
                if( safeSplit.get(i -1).substring(start, end).matches(".*[^0-9.]+.*") ||
                    str_i.substring(start, end).matches(".*[^0-9.]+.*") ||
                    safeSplit.get(i + 1).substring(start, end).matches(".*[^0-9.]+.*")){
                    res1 += Integer.parseInt(nb);
                }
                str_i = str_i.replaceFirst(nb, ".".repeat(nb.length())); // duplicate safegard
            }
        }

        // PART 2
        int res2 = 0;
        int lengthLine = input1.indexOf("\n") + 1;
        while(input1.contains("*")) {
            int idx = input1.indexOf("*");
            String tmpInput = input1;
            List<String> numbersList = Arrays.stream(input1.split("[^0-9]+")).toList();
            ArrayList<String> filteredNb = new ArrayList<String>();
            for(int i=0; i<numbersList.size(); i++){
                String nb = numbersList.get(i);
                if((tmpInput.indexOf(nb) + lengthLine >= idx - nb.length() &&
                        tmpInput.indexOf(nb)+nb.length() + lengthLine <= idx + nb.length() + 1) || (
                        tmpInput.indexOf(nb) >= idx - nb.length() &&
                        tmpInput.indexOf(nb)+nb.length() <= idx + nb.length() + 1) || (
                        tmpInput.indexOf(nb) - lengthLine >= idx - nb.length() &&
                        tmpInput.indexOf(nb)+nb.length() - lengthLine <= idx + nb.length()  + 1)){
                    filteredNb.add(nb);
                    if(filteredNb.size() == 2) break; // Assuming there isn't a third match
                }
                tmpInput = tmpInput.replaceFirst(nb, ".".repeat(nb.length())); // duplicate safegard
            }
            if(filteredNb.size() == 2){
                res2 += (Integer.parseInt(filteredNb.get(0)) * Integer.parseInt(filteredNb.get(1)));
            }
            input1 = input1.replaceFirst("[*]", ".");
        }

        System.out.println(res1);
        System.out.println(res2);
    }

}
