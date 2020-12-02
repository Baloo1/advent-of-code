import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./2020/Day2Input.txt");
        Scanner scanner = new Scanner(file);

        int problem1 = 0;
        int problem2 = 0;
        while (scanner.hasNextLine()) {
            String nextLine = (scanner.nextLine());

            String[] split = nextLine.split(": ");
            String[] letter = split[0].split(" ");
            String[] splitNbr = letter[0].split("-");
            int minNum = Integer.parseInt(splitNbr[0]);
            int maxNum = Integer.parseInt(splitNbr[1]);
            String letter1 = letter[1];

            /*Problem 1 */
            int lastIndex = 0;
            int count = 0;
            while (lastIndex != -1) {

                lastIndex = split[1].indexOf(letter1, lastIndex);

                if (lastIndex != -1) {
                    count++;
                    lastIndex += letter1.length();
                }
            }
            if (minNum <= count && count <= maxNum) {
                problem1++;
            }

            /*Problem 2 */
            String words = split[1];
            int nbr = 0;
            if (words.toCharArray()[minNum - 1] == letter1.toCharArray()[0]) {
                nbr++;
            }
            if (words.toCharArray()[maxNum - 1] == letter1.toCharArray()[0]) {
                nbr++;
            }
            if (nbr == 1) {
                problem2++;
            }
        }
        System.out.println("Problem 1: " + problem1);
        System.out.println("Problem 2: " + problem2);
    }
}

