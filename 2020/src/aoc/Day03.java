package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day03 {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\nDay 3");
        File file = new File("./2020/input/Day03Input.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<String> input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();

            input.add(nextLine);
        }

        /*Problem 1*/
        long problem1 = findTree(1, 3, input);
        System.out.println("Problem 1: " + problem1);

        /*Problem 2*/
        int[][] slopes = {{1, 1}, {1, 3}, {1, 5}, {1, 7}, {2, 1}};
        long problem2 = 1;
        for (int[] slope : slopes) {
            problem2 *= findTree(slope[0], slope[1], input);
        }
        System.out.println("Problem 2: " + problem2);
    }

    static long findTree(int down, int right, ArrayList<String> input) {
        int row = 0;
        int column = 0;
        int trees = 0;
        char tree = '#';
        while (row < input.size() - down) {
            /*Wraparound*/
            if (column > 30) {
                column -= 31;
            }

            char spot = input.get(row).charAt(column);
            if (spot == tree) {
                trees++;
            }
            row += down;
            column += right;
        }
        return trees;
    }
}

