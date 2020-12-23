package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day07 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./2020/Day07Input.txt");
        Scanner scanner = new Scanner(file);

        HashMap<String, String[]> bags = new HashMap<>();
        while (scanner.hasNextLine()) {
            /* Input parsing */
            String[] split = scanner.nextLine().split(" contain ");
            String bag = split[0].replace("bags", "").trim();
            String[] contents = split[1].trim().replace(".", "").split(", ");
            bags.put(bag, contents);
        }

        /*Problem 1*/
        HashSet<String> problem1 = new HashSet<>();
        problem1.add("shiny gold");
        boolean lookingForBag = true;

        while (lookingForBag) {
            Iterator<Map.Entry<String, String[]>> bagsIterator = bags.entrySet().iterator();
            lookingForBag = false;

            while (bagsIterator.hasNext()) {
                Map.Entry<String, String[]> entry = bagsIterator.next();
                String bag = entry.getKey();
                String contents = String.join(", ", entry.getValue());
                for (String bagBag : problem1) {
                    if (contents.contains(bagBag)) {
                        problem1.add(bag);
                        bagsIterator.remove();
                        lookingForBag = true;
                        break;
                    }
                }
            }
        }
        problem1.remove("shiny gold");
        System.out.println("Problem 1: " + problem1.size());

        /*Problem 2*/
        int problem2 = 0;
        Stack<String> bagsLeft = new Stack<>();
        bagsLeft.push("shiny gold");
        Stack<Integer> combinations = new Stack<>();
        combinations.push(1);

        while (!bagsLeft.isEmpty()) {
            String bag = bagsLeft.pop();
            int combination = combinations.pop();
            String[] contents = bags.get(bag);

            if (contents.length > 0 && !contents[0].equals("no other bags")) {
                for (String c : contents) {
                    String replace;
                    if (c.contains("bags")) {
                        replace = "bags";

                    } else {
                        replace = "bag";
                    }
                    String b = c.substring(c.indexOf(" ")).replace(replace, "").trim();
                    int n = Integer.parseInt(c.substring(0, c.indexOf(" ")).trim());
                    bagsLeft.push(b);
                    combinations.push(n * combination);
                    problem2 += n * combination;
                }
            }
        }
        System.out.println("Problem 2: " + problem2);
    }
}
