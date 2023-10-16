import java.util.Scanner;

public class Exercici2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el nom de l'alumne
        System.out.print("Introdueix el nom de l'alumne: ");
        String nomAlumne = scanner.nextLine();

        // Solicitar las notas de las 3 evaluaciones
        System.out.print("Introdueix la nota de la primera evaluaució: ");
        double nota1 = scanner.nextDouble();

        System.out.print("Introdueix la nota de la segona evaluaució: ");
        double nota2 = scanner.nextDouble();

        System.out.print("Introdueix la nota de la tercera evaluaució: ");
        double nota3 = scanner.nextDouble();

        // Calcular la mitja de les notes
        double mitja = (nota1 + nota2 + nota3) / 3;

        // Mostrar el resultat
        System.out.println("La mitja de les notes de l'alumne " + nomAlumne + " és: " + mitja);

        // Tancar l'escaner
        scanner.close();
    }
}

