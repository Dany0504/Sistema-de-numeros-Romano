package org.example;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static int ConvertirDecimal(String Noromano) throws IllegalArgumentException {
        int total = 0;
        int VActual = 0;
        int VAnterior = 0;

        for (int i = Noromano.length() - 1; i >= 0; i--) {
            char c = Noromano.charAt(i);
            // Asignar valor a cada letra
            switch (c) {
                case 'I':
                    VActual = 1;
                    break;
                case 'V':
                    VActual = 5;
                    break;
                case 'X':
                    VActual = 10;
                    break;
                case 'L':
                    VActual = 50;
                    break;
                case 'C':
                    VActual = 100;
                    break;
                case 'D':
                    VActual = 500;
                    break;
                case 'M':
                    VActual = 1000;
                    break;
                default:
                    //Excepcion invalida
                    throw new IllegalArgumentException(c + " no es un numero romano.");
            }
// Si el valor actual es menor que el anterior, restarlo en lugar de sumarlo
            if (VActual < VAnterior) {
                total -= VActual;
            } else {
                total += VActual;
            }

            VAnterior = VActual;
        }

        return total;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ingresa Un Argumento");
            return;
        }

        try (FileWriter csvWriter = new FileWriter("ConversionDecimal.csv")) {
            csvWriter.append("Romano,Decimal\n");

            for (String romano : args) {
                try {
                    int decimal = ConvertirDecimal(romano);
                    System.out.println("El número romano " + romano + " es igual a " + decimal + " en decimal.");

                csvWriter.append(romano).append(",").append(String.valueOf(decimal)).append("\n");
            } catch (IllegalArgumentException e) {
                // Excepcion para caracter invalido.
                System.err.println("Error: " + e.getMessage());
            }
        }
    } catch (IOException e) {
        System.err.println("Error al escribir el archivo CSV: " + e.getMessage());
    }
}
}



