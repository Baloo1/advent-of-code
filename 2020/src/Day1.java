import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class Day1 {
    public static HashMap<Integer, Integer> input = new HashMap<>();
    public static int triple = 0;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./2020/Day1Input.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            int nextLine = Integer.parseInt(scanner.nextLine());
            input.put(nextLine, nextLine);
        }

        /*Problem 1*/
        int problem1 = input.values().stream().filter((v) -> input.containsValue(2020 - v)).findFirst().get();
        System.out.println("Problem 1: " + (2020 - problem1) * problem1);

        /*Problem 2*/
        int problem2 = input.values().stream().filter((v) -> findTriple(2020 - v)).findFirst().get();
        System.out.println("Problem 2: " + problem2 * triple * (2020 - problem2 - triple));
    }

    public static boolean findTriple(Integer value) {
        Optional<Integer> optionalTriple = input.values().stream().filter(v -> input.containsKey(value - v)).findFirst();
        optionalTriple.ifPresent(integerIntegerEntry -> triple = integerIntegerEntry);
        return optionalTriple.isPresent();
    }
}

