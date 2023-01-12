import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Leitor {
    public static int lerInteiro(int min, int max) {
        return (int) lerLong(min, max);
    }

    public static long lerLong(long min, long max) {
        Scanner scanner = new Scanner(System.in);

        long input = -1;
        do {
            try {
                if (scanner.hasNextLong()) {
                    input = scanner.nextLong();
                } else {
                    throw new InputMismatchException("Valor invalido, introduza um valor inteiro: ");
                }

                if (input < min || input > max) {
                    throw new InputMismatchException("Valor invalido, introduza um valor entre " + min + " e " + max + ": ");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            scanner.nextLine();
        } while (input < min || input > max || input == -1);

        return input;
    }
}
