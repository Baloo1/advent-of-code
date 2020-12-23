package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day08 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\nDay 8");
        File file = new File("./2020/input/Day08Input.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<Pair<String, Integer>> input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            String[] split = nextLine.split(" ");
            String ins = split[0];
            int nbr = Integer.parseInt(split[1]);
            input.add(new Pair<>(ins, nbr));
        }

        /*Problem 1*/
        int problem1 = runAssembly(input).right();
        System.out.println("Problem 1: " + problem1);

        /*Problem 2*/
        int problem2 = 0;
        for (int i = 0; i < input.size(); i++) {
            String inst = input.get(i).left();
            int val = input.get(i).right();
            if (!inst.equals("acc")) {
                Pair<Integer, Integer> testProgram = new Pair<>(0, 0);
                /*This check could use some cleanup or generalizing*/
                if (inst.equals("jmp")) {
                    input.set(i, new Pair<>("nop", val));
                    testProgram = runAssembly(input);
                    input.set(i, new Pair<>("jmp", val));
                } else if (inst.equals("nop")) {
                    input.set(i, new Pair<>("jmp", val));
                    testProgram = runAssembly(input);
                    input.set(i, new Pair<>("nop", val));
                }
                if (testProgram.left() == input.size()) {
                    problem2 = testProgram.right();
                    break;
                }
            }
        }
        System.out.println("Problem 2: " + problem2);
    }

    /*Should be reusable*/
    public static Pair<Integer, Integer> runAssembly(ArrayList<Pair<String, Integer>> instructions) {
        /*Problem 1*/
        int pc = 0;
        int acc = 0;
        boolean[] previousInstructions = new boolean[instructions.size()];
        while (pc >= 0 && pc < instructions.size() && !previousInstructions[pc]) {
            previousInstructions[pc] = true;
            String inst = instructions.get(pc).left();
            int val = instructions.get(pc).right();
            if (inst.equals("acc")) {
                acc += val;
                pc += 1;
            } else if (inst.equals("jmp")) {
                pc += val;
            } else {
                pc += 1;
            }
        }
        return new Pair<>(pc, acc);
    }
}