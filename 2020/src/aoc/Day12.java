package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day12 {
    static char[] directions = {'E', 'S', 'W', 'N'};
    static int x = 0;
    static int y = 0;
    static int direction = 0;
    static int wayX = 10;
    static int wayY = 1;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\nDay 12");
        File file = new File("./2020/input/Day12Input.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<Pair<Character, Integer>> instructions = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split("(?<=\\D)(?=\\d)");
            instructions.add(new Pair<>(input[0].charAt(0), Integer.parseInt(input[1])));
        }

        /* Problem 1 */
        for (Pair<Character, Integer> pair : instructions) {
            char instruction = pair.left();
            int speed = pair.right();
            problem1(instruction, speed);
        }
        int problem1 = Math.abs(x) + Math.abs(y);
        System.out.println("Problem 1: " + problem1);

        /* Problem 2 */
        x = 0;
        y = 0;
        for (Pair<Character, Integer> pair : instructions) {
            char instruction = pair.left();
            int speed = pair.right();
            problem2(instruction, speed);
        }
        int problem2 = Math.abs(x) + Math.abs(y);
        System.out.println("Problem 2: " + problem2);
    }

    static void problem1(char instruction, int speed) {
        switch (instruction) {
            case 'E' -> x += speed;
            case 'S' -> y -= speed;
            case 'W' -> x -= speed;
            case 'N' -> y += speed;
            case 'L' -> direction = (4 + direction - speed / 90) % 4;
            case 'R' -> direction = (direction + speed / 90) % 4;
            case 'F' -> problem1(directions[direction], speed);
        }
    }

    static void problem2(char instruction, int speed) {
        switch (instruction) {
            case 'E' -> wayX += speed;
            case 'S' -> wayY -= speed;
            case 'W' -> wayX -= speed;
            case 'N' -> wayY += speed;
            case 'L' -> {
                for (int i = 0; i < speed; i += 90) {
                    wayX = wayX + wayY;
                    wayY = wayX - wayY;
                    wayX = wayX - wayY;
                    wayX *= -1;
                }
            }
            case 'R' -> {
                for (int i = 0; i < speed; i += 90) {
                    wayX = wayX + wayY;
                    wayY = wayX - wayY;
                    wayX = wayX - wayY;
                    wayY *= -1;
                }
            }
            case 'F' -> {
                x += wayX * speed;
                y += wayY * speed;
            }
        }
    }
}