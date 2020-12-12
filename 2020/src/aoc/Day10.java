package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./2020/Day10Input.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<Integer> input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            Integer nbr = Integer.parseInt(nextLine);
            input.add(nbr);
        }

        /*Problem 1*/
        int problem1 = 0;
        input.sort(Integer::compareTo);
        int joltDiff = input.get(0);
        int smolJolt = 0;
        int chonkyJolt = 1;
        for (int i = 1; i <= input.size(); i++) {
            if (joltDiff == 1) {
                smolJolt++;
            } else if (joltDiff == 3) {
                chonkyJolt++;
            }
            if (i != input.size())
                joltDiff = input.get(i) - input.get(i - 1);
        }
        problem1 = smolJolt * chonkyJolt;
        System.out.println(problem1);

        /*Problem 2*/
        long problem2 = 0;
        long[] manyAdapters = new long[input.get(input.size() - 1) + 1];
        manyAdapters[0] = 1;
        for (Integer integer : input) {
            long x = 0;
            long y = 0;
            long z = 0;
            if (integer >= 3) {
                x = manyAdapters[integer - 3];
            }
            if (integer >= 2) {
                y = manyAdapters[integer - 2];
            }
            if (integer >= 1) {
                z = manyAdapters[integer - 1];
            }
            manyAdapters[integer] = x + y + z;
        }
        problem2 = manyAdapters[manyAdapters.length - 1];
        System.out.println(problem2);
    }
}