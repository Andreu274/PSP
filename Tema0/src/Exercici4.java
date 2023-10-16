import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exercici4 {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        boolean salir = false;

        while (!salir) {
            System.out.println("Menu:");
            System.out.println("1. Llegir una cadena (String)");
            System.out.println("2. Llegir un enter (int)");
            System.out.println("3. Llegir un nombre decimal (float)");
            System.out.println("4. Llegir un nombre decimal (double)");
            System.out.println("5. Sortir");

            try {
                System.out.print("Selecciona una opció: ");
                int opcio = Integer.parseInt(br.readLine());

                switch (opcio) {
                    case 1:
                        System.out.print("Introdueix una cadena: ");
                        String cadena = br.readLine();
                        System.out.println("Cadena introduïda: " + cadena);
                        break;
                    case 2:
                        System.out.print("Introdueix un número enter: ");
                        int enter = Integer.parseInt(br.readLine());
                        System.out.println("Enter introduït: " + enter);
                        break;
                    case 3:
                        System.out.print("Introdueix un nombre decimal (float): ");
                        float decimalFloat = Float.parseFloat(br.readLine());
                        System.out.println("Nombre decimal (float) introduït: " + decimalFloat);
                        break;
                    case 4:
                        System.out.print("Introdueix un nombre decimal (double): ");
                        double decimalDouble = Double.parseDouble(br.readLine());
                        System.out.println("Nombre decimal (double) introduït: " + decimalDouble);
                        break;
                    case 5:
                        System.out.println("Au idoi, adeu!");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opció no vàlida. Torna a seleccionar.");
                        break;
                }
            } catch (IOException e) {
                System.err.println("Error d'entrada/sortida: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Error de format: Has d'introduir un número vàlid.");
            }
        }

        try {
            br.close();
            isr.close();
        } catch (IOException e) {
            System.err.println("Error al tancar els fluxos: " + e.getMessage());
        }
    }
}
