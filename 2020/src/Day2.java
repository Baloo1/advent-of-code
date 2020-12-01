import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day2 {
    public static HashMap<Integer, Integer> input = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Projects\\AdventOfCode\\Day2Input.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            int nextLine = Integer.parseInt(scanner.nextLine());
            input.put(nextLine, nextLine);
        }
    }
}

