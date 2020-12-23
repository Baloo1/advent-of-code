package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day02 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./2020/Day2Input.txt");
        Scanner scanner = new Scanner(file);

        int problem1 = 0;
        int problem2 = 0;
        while (scanner.hasNextLine()) {
            String nextLine = (scanner.nextLine());

            /*Input parsing*/
            String[] split = nextLine.split(": ");
            String[] letterArray = split[0].split(" ");
            String[] splitNbr = letterArray[0].split("-");
            int minNum = Integer.parseInt(splitNbr[0]);
            int maxNum = Integer.parseInt(splitNbr[1]);
            String letter = letterArray[1];

            /*Problem 1 */
            int lastIndex = 0;
            int count = 0;
            while (lastIndex != -1) {
                lastIndex = split[1].indexOf(letter, lastIndex);

                if (lastIndex != -1) {
                    count++;
                    lastIndex += letter.length();
                }
            }
            if (minNum <= count && count <= maxNum) {
                problem1++;
            }

            /*Problem 2 */
            char lowIndex = split[1].toCharArray()[minNum - 1];
            char highIndex = split[1].toCharArray()[maxNum - 1];
            char letterToCompare = letter.toCharArray()[0];
            if (lowIndex == letterToCompare ^ highIndex == letterToCompare) {
                problem2++;
            }
        }
        System.out.println("Problem 1: " + problem1);
        System.out.println("Problem 2: " + problem2);
    }
}

