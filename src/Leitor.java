import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Leitor {
    public static int lerInteiro(int min, int max) {
        int lido = -1;
        boolean verificar;

        do {
            try {
                lido = (int) lerDouble(min, max);
                verificar = true;
            } catch (Exception e) {
                System.out.println("Introduza um numero valido.");
                verificar = false;
            }
        } while (!verificar);

        return lido;
    }

    public static long lerLong(long min, long max) {
        long lido = -1;
        boolean verificar;

        do {
            try {
                lido = (long) lerDouble(min, max);
                verificar = true;
            } catch (Exception e) {
                System.out.println("Introduza um numero valido.");
                verificar = false;
            }
        } while (!verificar);

        return lido;
    }

    public static double lerDouble(double min, double max) {
        Scanner scanner = new Scanner(System.in);

        double input = -1;
        do {
            try {
                if (scanner.hasNextDouble()) {
                    input = scanner.nextDouble();
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
        return lerStuff(9, "NIF invalido, introduza um NIF valido: ");
    }

    public static int lerPin() {
        return (int) lerStuff(4, "PIN invalido, introduza um PIN valido: ");
    }

    private static long lerStuff(long length, String errorMsg) {
        long stuff = 0;

        do {
            try {
                stuff = Long.parseLong(lerString(length));
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        } while (stuff == 0);

        return stuff;
    }

    public static String lerString(long tamanho) {
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