import java.util.Scanner;

public class Exercici3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el nombre de dies que fa feina
        System.out.print("Introdueix el nombre de dies que fas feina al mes: ");
        double nomDies = scanner.nextDouble();

        // Solicitar el nombre d'hores que fa al dia el treballador
        System.out.print("Introdueix el nombre d'hores que fas al dia: ");
        double nomHores = scanner.nextDouble();

        // Solicitar a quant cobra les hores el treballador
        System.out.print("Introdueix la quantitat de doblers que et paguen per hora: ");
        double preuHora = scanner.nextDouble();

        // Calcular la mitja de les notes
        double sou = (nomDies * nomHores * preuHora);

        // Mostrar el resultat
        System.out.println("El teu sou es de: " + sou + " euros al mes");

        // Tancar l'escaner
        scanner.close();
    }
}
