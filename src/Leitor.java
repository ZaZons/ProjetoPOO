import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Leitor {
    /**
     * Lê um inteiro, tendo em conta os mínimos e máximos introduzidos.
     */
    public static int lerInteiro(int min, int max) {
        Scanner scanner = new Scanner(System.in);

        int input = -1;
        do {
            try {
                if (scanner.hasNextInt()) {
                    input = scanner.nextInt();
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

    /**
     * Lê um long, tendo em conta os mínimos e máximos introduzidos.
     */
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

    /**
     * Lê um double, tendo em conta os mínimos e máximos introduzidos.
     */
    public static double lerDouble(double min, double max) {
        Scanner scanner = new Scanner(System.in);

        double input = -1;
        do {
            try {
                if (scanner.hasNextDouble()) {
                    input = scanner.nextDouble();
                } else {
                    throw new InputMismatchException("Valor invalido, introduza um valor decimal: ");
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

    /**
     * Lê o NIF do utilizador e devolve-o.
     */
    public static long lerNif() {
        Scanner scanner = new Scanner(System.in);

        int tamanho = 9;
        String input;
        boolean verificado;
        long res = 0;
        do {
            try {
                input = scanner.nextLine();
                if(input.isEmpty()) {
                    throw new InputMismatchException("\nString introduzida não pode ser vazia");
                }

                if (input.length() != tamanho) {
                    throw new InputMismatchException("\nValor invalido, introduza um valor com " + tamanho + " digitos: ");
                } else {
                    verificado = true;
                }

                res = Long.parseLong(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                verificado = false;
            }
        } while (!verificado);

        return res;
    }

    /**
     * Lê o PIN do método de pagamento do utilizador e devolve-o.
     */
    public static int lerPin() {
        Scanner scanner = new Scanner(System.in);

        int tamanho = 4;
        String input;
        boolean verificado;
        int res = 0;
        do {
            try {
                input = scanner.nextLine();
                if(input.isEmpty()) {
                    throw new InputMismatchException("\nString introduzida não pode ser vazia");
                }

                if (input.length() != tamanho) {
                    throw new InputMismatchException("\nValor invalido, introduza um valor com " + tamanho + " digitos: ");
                } else {
                    verificado = true;
                }

                res = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                verificado = false;
            }
        } while (!verificado);

        return res;
    }

    /**
     * Lê uma String introduzida e devolve-a
     */
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