import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class that takes input of numbers between 1 and 100, and adds an asterisk for each number in ranges 1-10, 11-20...., 91-100.
 * Enter a number outside the range to end the loop.
 */
public class Histogram {
    public static void main(String[] args) {
        try {
            int[] ranges = new int[10];
            int box;
            int lowerBound;
            int upperBound;

            Scanner scan = new Scanner(System.in);

            System.out.println("Enter numbers between 1 and 100.");
            System.out.println("End program by entering a number outside of that range.");

            int input = scan.nextInt();
            while (input > 0 && input <= 100) {
                box = (input - 1) / 10;
                ranges[box]++;
                input = scan.nextInt();
            }

            for (box = 0; box < 10; box++) {
                lowerBound = box * 10 + 1;
                upperBound = (box + 1) * 10;
                System.out.print(lowerBound + "-" + upperBound + "\t| ");
                for (int i = 0; i < ranges[box]; i++)
                    System.out.print('*');
                System.out.println();
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Inappropriate input. Enter a number.");
        }
    }
}
