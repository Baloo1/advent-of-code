package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day11 {
    static int width;
    static int height;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\nDay 11");
        File file = new File("./2020/input/Day11Input.txt");
        Scanner scanner = new Scanner(file);

        /*Conways game of ferry*/
        ArrayList<char[]> matrixOfLife = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            matrixOfLife.add(input.toCharArray());
        }
        height = matrixOfLife.size();
        width = matrixOfLife.get(0).length;

        /*Problem 1*/
        char[][] previousGeneration = matrixOfLife.toArray(new char[0][0]);
        char[][] generation = futureGeneration(previousGeneration, false, 4);
        while (!Arrays.deepEquals(generation, previousGeneration)) {
            previousGeneration = generation;
            generation = futureGeneration(generation, false, 4);
        }
//        System.out.println("\n\n Final Generation Problem 1 \n\n");
        int problem1 = calculateSeats(generation);

        /*Problem 2*/
//        System.out.println("\n\n Starting Problem 2 \n\n");
        previousGeneration = matrixOfLife.toArray(new char[0][0]);
        generation = futureGeneration(previousGeneration, true, 5);
        while (!Arrays.deepEquals(generation, previousGeneration)) {
            previousGeneration = generation;
            generation = futureGeneration(generation, true, 5);
        }
//        System.out.println("\n\n Final Generation Problem 2 \n\n");
        int problem2 = calculateSeats(generation);

        System.out.println("Problem 1: " + problem1);
        System.out.println("Problem 2: " + problem2);
    }

    static int calculateSeats(char[][] generation) {
        int occupiedSeat = 0;
        for (char[] row : generation) {
            for (char seat : row) {
                if (seat == '#') {
                    occupiedSeat++;
                }
            }
        }
        return occupiedSeat;
    }

    static void printGeneration(char[][] generation) {
        System.out.println("\n Generation \n");
        for (char[] row : generation) {
            for (char seat : row) {
                if (seat == 'L')
                    System.out.print("L");
                else if (seat == '.') {
                    System.out.print(".");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }

    static char[][] futureGeneration(char[][] generation, boolean sightSearch, int seatsForSwitch) {
        char[][] future = new char[generation.length][generation[0].length];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int filledSeat = 0;
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (k == 0 && l == 0) continue;
                        if (!sightSearch) {
                            filledSeat += problem1(generation, i + k, j + l);
                        } else {
                            filledSeat += problem2(generation, i + k, j + l, k, l);
                        }

                    }
                }

                if ((generation[i][j] == 'L') && (filledSeat == 0)) {
                    future[i][j] = '#';
                } else if ((generation[i][j] == '#') && (filledSeat >= seatsForSwitch)) {
                    future[i][j] = 'L';
                } else {
                    future[i][j] = generation[i][j];
                }
            }
        }
        //  printGeneration(future);
        return future;
    }

    static boolean inside(int currHeight, int currWidth) {
        return currWidth >= 0 && currHeight >= 0 && currWidth < width && currHeight < height;
    }

    static int problem1(char[][] generation, int currHeight, int currWidth) {
        if (inside(currHeight, currWidth) && generation[currHeight][currWidth] == '#') {
            return 1;
        }
        return 0;
    }

    static int problem2(char[][] generation, int currHeight, int currWidth, int heightOffset, int widthOffset) {
        while (inside(currHeight, currWidth)) {
            if (generation[currHeight][currWidth] == '#') {
                return 1;
            } else if (generation[currHeight][currWidth] == 'L') {
                return 0;
            }
            currHeight += heightOffset;
            currWidth += widthOffset;
        }
        return 0;
    }
}