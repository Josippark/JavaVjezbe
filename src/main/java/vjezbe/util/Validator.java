package vjezbe.util;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {

    public static BigDecimal checkBigDecimal(Scanner scanner, BigDecimal value, String prompt) {
        boolean continueLoop = false;
        do {
            try {
                System.out.println(prompt);
                value = scanner.nextBigDecimal();
            } catch (InputMismatchException | ArithmeticException ex) {
                System.out.println("Pogrešan unos, ponovno unesite vrijednost:");
                scanner.nextLine();
                continueLoop = true;
            }
        } while (continueLoop);
        return value;
    }

}
