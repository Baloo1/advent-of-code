package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day13 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\nDay 13");
        File file = new File("./2020/input/Day13Input.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<Long> busIDs = new ArrayList<>();
        long earliest = 0L;
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (scanner.hasNextLine()) {
                earliest = Long.parseLong(input);
            } else {
                for (String id : input.split(",")) {
                    busIDs.add(id.equals("x") ? -1 : Long.parseLong(id));
                }
            }
        }

        /* Problem 1 */
        long problem1 = 0L;
        long departureTime = earliest;
        while (problem1 == 0L) {
            for (long busID : busIDs) {
                if (busID < 0L) {
                    continue;
                }
                if (departureTime % busID == 0L) {
                    problem1 = busID * (departureTime - earliest);
                }
            }
            departureTime++;
        }
        System.out.println("Problem 1: " + problem1);

        /* Problem 2 */
        long problem2 = 0L;
        long LCM = busIDs.get(0);
        long t = LCM;
        int index = 1;
        while (problem2 == 0L) {
            long id = busIDs.get(index);
            if (id == -1) {
                index++;
                continue;
            }
            if ((t + index) % id == 0L) {
                index++;
                if (index >= busIDs.size()) {
                    problem2 = t;
                }
                LCM *= id;
                continue;
            }
            t += LCM;
        }
        System.out.println("Problem 2: " + problem2);
    }
}
