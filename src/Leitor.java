import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Leitor {
    public static int lerInteiro(int min, int max) {
        // todo numberformatexception
        return (int) lerDouble(min, max);
    }

    public static long lerLong(long min, long max) {
        // todo numberformatexception
        return (long) lerDouble(min, max);
    }

    public static double lerDouble(double min, double max) {
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

    public static String lerString(long min, long max) {
        Scanner scanner = new Scanner(System.in);

        // todo exception valores decimais
        String input = "";
        do {
            try {
                if (scanner.hasNextLine()) {
                    input = scanner.nextLine();
                } else {
                    throw new InputMismatchException("Valor invalido, introduza um valor inteiro: ");
                }

                if (input.length() < min || input.length() > max) {
                    throw new InputMismatchException("Valor invalido, introduza um valor entre " + min + " e " + max + ": ");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            scanner.nextLine();
        } while (input.length() < min || input.length() > max || input == "");

        return input;
    }
}