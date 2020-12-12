package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./2020/Day9Input.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<Long> input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            long nbr = Long.parseLong(nextLine);
            input.add(nbr);
        }

        /*Problem 1*/
        long problem1 = 0;
        LinkedHashSet<Long> last25 = new LinkedHashSet<>();
        for (int i = 0; i < 25; i++) {
            last25.add(input.get(i));
        }
        for (int i = 25; i < input.size(); i++) {
            boolean found = false;
            long currentValue = input.get(i);
            for (Long e : last25) {
                if (last25.contains(currentValue - e)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                last25.remove(last25.iterator().next());
                last25.add(currentValue);
            } else {
                problem1 = currentValue;
                break;
            }
        }
        System.out.println(problem1);

        /*Problem 2*/
        long problem2 = 0;
        long currentSum = input.get(0);
        int start = 0;
        for (int i = 1; i < input.size(); i++) {
            while (currentSum > problem1) {
                currentSum = currentSum - input.get(start);
                start++;
            }
            if (currentSum == problem1) {
                int end = i - 1;
                List<Long> contSubSet = input.subList(start, end);
                problem2 = Collections.min(contSubSet) + Collections.max(contSubSet);
                break;
            }
            currentSum = currentSum + input.get(i);
        }
        System.out.println(problem2);
    }
}
