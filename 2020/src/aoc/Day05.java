package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day05 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./2020/Day5Input.txt");
        Scanner scanner = new Scanner(file);

        /*Problem 1*/
        ArrayList<Integer> input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();

            int front = 0;
            int back = 127;
            int row = 0;
            int top = 7;
            int bot = 0;
            int last = 0;
            for (int i = 0; i < 10; i++) {
                if (i < 6) {
                    if (nextLine.charAt(i) == 'F') {
                        back = (int) (Math.floor((back + front) / 2.0));
                    } else if (nextLine.charAt(i) == 'B') {
                        front = (int) Math.ceil((back + front) / 2.0);
                    }
                }
                if (i == 6) {
                    if (nextLine.charAt(i) == 'F') {
                        row = front;
                    } else if (nextLine.charAt(i) == 'B') {
                        row = back;
                    }
                }
                if (i > 6 && i < 9) {
                    if (nextLine.charAt(i) == 'R') {
                        bot = (int) Math.ceil((bot + top) / 2.0);
                    } else if (nextLine.charAt(i) == 'L') {
                        top = (int) Math.floor((bot + top) / 2.0);
                    }
                }
                if (i == 9) {
                    if (nextLine.charAt(i) == 'R') {
                        last = top;
                    } else if (nextLine.charAt(i) == 'L') {
                        last = bot;
                    }
                }
            }
            input.add(row * 8 + last);
        }
        int problem1 = Collections.max(input);
        System.out.println("Problem 1 :" + problem1);

        /*Problem 2*/
        Collections.sort(input);
        int problem2 = 0;
        int right = input.size() / 2;
        int left = input.size() / 2;
        while (problem2 == 0) {
            int rightValue = input.get(right);
            int leftValue = input.get(left);
            if (rightValue - input.get(right - 1) != 1) {
                problem2 = rightValue - 1;
            }
            if (leftValue - input.get(left - 1) != 1) {
                problem2 = leftValue - 1;
            }
            right++;
            left--;
        }
        System.out.println("Problem 2: " + problem2);
    }
}