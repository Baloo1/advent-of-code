package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day15 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./2020/Day15Input.txt");
        Scanner scanner = new Scanner(file);

        int[] input = new int[6];
        while (scanner.hasNextLine()) {
            input = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        }

        /* Problem 1 */
        int problem1 = playGame(input, 2020);
        System.out.println("Problem 1: " + problem1);

        /* Problem 2 */
        int problem2 = playGame(input, 30000000);
        System.out.println("Problem 2: " + problem2);
    }

    static int playGame(int[] input, int rounds) {
        int[] gameNumbers = new int[rounds];
        for (int i = 0; i < input.length - 1; i++) {
            gameNumbers[input[i]] = i + 1;
        }
        int currentNumber = input[input.length - 1];
        for (int currentRound = input.length; currentRound < gameNumbers.length; currentRound++) {
            if (gameNumbers[currentNumber] == 0) {
                gameNumbers[currentNumber] = currentRound;
                currentNumber = 0;
            } else {
                int last = gameNumbers[currentNumber];
                gameNumbers[currentNumber] = currentRound;
                currentNumber = currentRound - last;
            }
        }
        return currentNumber;
    }
}