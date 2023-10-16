import java.io.*;
import java.util.Scanner;

public class Exercici5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nomFitxerLectura;
        String nomFitxerEscriptura;

        int opcio = 0;

        while (opcio != 3) {
            System.out.println("\nMENU:");
            System.out.println("1. Llegir el contingut del fitxer de lectura.");
            System.out.println("2. Escriure text al final del fitxer d'escriptura.");
            System.out.println("3. Sortir.");

            System.out.print("Selecciona una opció (1/2/3): ");
            opcio = scanner.nextInt();
            scanner.nextLine(); // Consumir la nova línia

            if (opcio == 1) {
                // Demanar a l'usuari el nom del fitxer de lectura
                System.out.print("Introdueix el nom del fitxer que vols llegir: ");
                nomFitxerLectura = scanner.nextLine();

                llegir(nomFitxerLectura);
            } else if (opcio == 2) {
                // Demanar a l'usuari el nom del fitxer d'escriptura
                System.out.print("Introdueix el nom del fitxer on vols escriure: ");
                nomFitxerEscriptura = scanner.nextLine();

                System.out.print("Introdueix el text que vols afegir: ");
                String text = scanner.nextLine();
                escriure(nomFitxerEscriptura, text);
            } else if (opcio == 3) {
                System.out.println("Sortint del programa.");
            } else {
                System.out.println("Opció no vàlida. Torna a intentar.");
            }
        }

        scanner.close();
    }

    // Mètode per llegir i mostrar el contingut d'un fitxer
    /*Aquest mètode rep com a paràmetre el nom d'un fitxer que es vol llegir (nomFitxerLectura). El que fa és el següent:
    Comprova si el fitxer existeix. Si el fitxer no existeix, imprimeix un missatge d'error i surt del mètode.
    Crea un File que representa el fitxer de lectura.
    Inicialitza un FileReader per llegir el contingut del fitxer.
    Crea un BufferedReader per millorar l'eficiència de la lectura.
    Llegeix línia per línia del fitxer utilitzant un bucle while fins a arribar al final del fitxer. Les línies es mostren per pantalla.
    Després de llegir totes les línies, tanca el BufferedReader.
    Si es produeix un error d'entrada/sortida, captura l'excepció i imprimeix un missatge d'error.*/
    public static void llegir(String nomFitxerLectura) {
        try {
            File fitxerLectura = new File(nomFitxerLectura);
            if (!fitxerLectura.exists()) {
                System.out.println("El fitxer de lectura no existeix.");
                return;
            }

            FileReader lectorFitxer = new FileReader(fitxerLectura);
            BufferedReader lectorBuffer = new BufferedReader(lectorFitxer);

            String linia;
            System.out.println("Contingut del fitxer " + nomFitxerLectura + ":");
            System.out.println("----------------------");
            while ((linia = lectorBuffer.readLine()) != null) {
                System.out.println(linia);
            }
            System.out.println("======================================================================");

            lectorBuffer.close();
        } catch (IOException e) {
            System.err.println("Error en llegir el fitxer de lectura: " + e.getMessage());
        }
    }

    // Mètode per escriure text al final d'un fitxer
    /*Aquest mètode rep dos paràmetres: el nom del fitxer d'escriptura (nomFitxerEscriptura) i el text que es vol afegir al fitxer (text). Aquest mètode fa el següent:
    Crea un File que representa el fitxer d'escriptura.
    Inicialitza un FileWriter per escriure al fitxer. L'argument true indica que s'ha d'afegir el text al final del fitxer en cas que ja existeixi.
    Crea un BufferedWriter per millorar l'eficiència de l'escriptura.
    Escriu el text rebut com a paràmetre al fitxer.
    Afegeix una nova línia després del text per separar el nou contingut.
    Tanca el BufferedWriter per assegurar-se que les dades es guarden al fitxer.
    Imprimeix un missatge d'èxit per indicar que el text s'ha afegit correctament al fitxer.
    Si es produeix un error d'entrada/sortida, captura l'excepció i imprimeix un missatge d'error.*/
    public static void escriure(String nomFitxerEscriptura, String text) {
        try {
            File fitxerEscriptura = new File(nomFitxerEscriptura);
            FileWriter escriptorFitxer = new FileWriter(fitxerEscriptura, true); // El true indica que s'afegeix al final del fitxer
            BufferedWriter escriptorBuffer = new BufferedWriter(escriptorFitxer);

            escriptorBuffer.write(text);
            escriptorBuffer.newLine(); // Afegir una nova línia després del text

            escriptorBuffer.close();
            System.out.println("Text afegit al fitxer " + nomFitxerEscriptura);
            System.out.println("======================================================================");
        } catch (IOException e) {
            System.err.println("Error en escriure al fitxer d'escriptura: " + e.getMessage());
        }
    }
}
