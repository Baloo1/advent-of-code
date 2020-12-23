package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day14 {
    static HashMap<Integer, String> problem1Register = new HashMap<>();
    static HashMap<String, Integer> problem2Register = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\nDay 14");
        File file = new File("./2020/input/Day14Input.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<String> input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            input.add(scanner.nextLine());
        }

        /* Problem 1 & Problem 2 */
        long problem1 = 0;
        long problem2 = 0;

        String mask = "";
        for (String instruction : input) {
            String bits = instruction.substring(instruction.indexOf("=") + 2);
            if (instruction.startsWith("mem")) {
                problem1(instruction, bits, mask);
                problem2(instruction, bits, mask);
            } else if (instruction.startsWith("mask")) {
                mask = bits;
            }
        }
        for (String memValue : problem1Register.values()) {
            problem1 += Long.parseLong(memValue, 2);
        }
        System.out.println("Problem 1: " + problem1);

        for (int memValue : problem2Register.values()) {
            problem2 += memValue;
        }
        System.out.println("Problem 2: " + problem2);
    }

    static void problem1(String instruction, String bits, String mask) {
        int address = Integer.parseInt(instruction.substring(4, instruction.indexOf("]")));
        String bit = binaryString(bits);
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) != 'X') {
                bit = bit.substring(0, i) + mask.charAt(i) + bit.substring(i + 1);
            }
        }
        problem1Register.put(address, bit);
    }

    static void problem2(String instruction, String bits, String mask) {
        String address = binaryString(instruction.substring(4, instruction.indexOf("]")));
        int value = Integer.parseInt(bits);
        ArrayList<String> addresses = applyMask(address, mask, 0);
        for (String memAddress : addresses) {
            problem2Register.put(memAddress, value);
        }
    }

    static String binaryString(String address) {
        return String.format("%36s", Integer.toBinaryString(Integer.parseInt(address))).replace(' ', '0');
    }

    static ArrayList<String> applyMask(String address, String mask, int index) {
        if (index == address.length()) {
            ArrayList<String> completedMask = new ArrayList<>();
            completedMask.add(address);
            return completedMask;
        }
        String newAddress = address.substring(0, index) + "1" + address.substring(index + 1);
        if (mask.charAt(index) == '1') {
            return applyMask(newAddress, mask, index + 1);
        } else if (mask.charAt(index) == 'X') {
            ArrayList<String> mask1 = applyMask(newAddress, mask, index + 1);
            newAddress = newAddress.substring(0, index) + "0" + newAddress.substring(index + 1);
            ArrayList<String> mask0 = applyMask(newAddress, mask, index + 1);
            mask1.addAll(mask0);
            return mask1;
        }
        return applyMask(address, mask, index + 1);
    }
}

