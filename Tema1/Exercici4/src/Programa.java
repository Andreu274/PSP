import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix el nom de fitxer (p.e. sortida.txt): ");
        String nomFitxer = scanner.nextLine();

        // Executar ExecutarDir
        ExecutarDir.main(null);

        // Executar ExecutarFind amb el text introduït per l'usuari
        System.out.print("Introdueix el text a cercar a sortida.txt: ");
        String textCerca = scanner.nextLine();
        ExecutarFind.main(new String[]{textCerca});
    }
}
