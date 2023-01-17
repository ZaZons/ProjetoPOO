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

    public static long lerNif() {
        return lerStuff(9);
    }

    public static int lerPin() {
        return (int) lerStuff(4);
    }

    private static long lerStuff(long length) {
        long stuff = 0;

        do {
            try {
                stuff = Long.parseLong(lerString(length));
            } catch (Exception e) {
                System.out.println("PIN invalido, introduza um pin valido: ");
            }
        } while (stuff == 0);

        return stuff;
    }

    public static String lerString(long tamanho) { // TODO: verificar se deixar min max ou só "min"
        Scanner scanner = new Scanner(System.in);

        String input = "";
        boolean verificado = true;
        do {
            try {
                input = scanner.nextLine();
                if(input.isEmpty()) {
                    throw new InputMismatchException("\nString introduzida não pode ser vazia");
                }

                if (tamanho != -1) {
                    if (input.length() != tamanho) {
                        throw new InputMismatchException("\nValor invalido, introduza um valor com " + tamanho + " digitos: ");
                    } else {
                        verificado = true;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                verificado = false;
            }
        } while (!verificado);

        return input;
    }
}