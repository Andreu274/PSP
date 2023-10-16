import java.io.*;

public class ExecutarDir {
    public static void main(String[] args) {
        // Definir el nom de l'arxiu
        String nomFitxer = "sortida.txt";

        try {
            // Crear l'arxiu sortida.txt
            File fitxer = new File(nomFitxer);
            if (fitxer.createNewFile()) {
                System.out.println("Fitxer creat: " + nomFitxer);
            } else {
                System.out.println("El fitxer ja existeix. Sobrescrivint...");
            }

            // Executar la comanda dir i emmagatzemar el resultat a sortida.txt
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "dir");
            processBuilder.directory(new File(System.getProperty("user.home") + "\\Desktop"));
            processBuilder.redirectOutput(fitxer);
            Process process = processBuilder.start();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
