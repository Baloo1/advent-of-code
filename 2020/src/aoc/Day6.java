package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./2020/Day6Input.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<ArrayList<String>> allGroups = new ArrayList<>();
        ArrayList<String> group = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                group.add(input);
                if (!scanner.hasNext()) {
                    allGroups.add(group);
                    group = new ArrayList<>();
                }
            } else {
                allGroups.add(group);
                group = new ArrayList<>();
            }
        }
        /*Problem 1*/
        /* Did you know that chars() is an IntStream you have to cast to char from? */
        int problem1 = allGroups.stream().map(s -> String.join("", s).chars().mapToObj(c -> Character.toString((char) c)).distinct().mapToInt(String::length).sum()).mapToInt(i -> i).sum();
        System.out.println(problem1);
    }
}