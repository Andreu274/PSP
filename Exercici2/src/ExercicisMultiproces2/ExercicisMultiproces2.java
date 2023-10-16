package ExercicisMultiproces2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class ExercicisMultiproces2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Crea el procés fill
        ProcessBuilder processBuilder = new ProcessBuilder("java", "ExercicisMultiproces2_ModificarString");
        Process process = processBuilder.start();

        // Configura els streams de comunicació amb el procés fill
        OutputStream outputStream = process.getOutputStream();
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        // Llegeix línies de l'entrada estàndard i les envia al procés fill
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            outputStream.write((line + "\n").getBytes());
            outputStream.flush();
            // Llegeix la resposta del procés fill i imprimeix
            String output = inputStream.readLine();
            if (output != null) {
                System.out.println("El PARE diu: " + output);
            }
        }

        // Espera que el procés fill acabi
        process.waitFor();

        // Llegeix errors del procés fill (si n'hi ha) i els imprimeix
        String error;
        while ((error = errorStream.readLine()) != null) {
            System.err.println("Error: " + error);
        }

        // Tanca els streams
        reader.close();
        inputStream.close();
        outputStream.close();
        errorStream.close();
    }
}
