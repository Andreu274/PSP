package ExercicisMultiproces3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ExercicisMultiproces3 {
    public static void main(String[] args) throws IOException {
        // Crea el procés fill
        ProcessBuilder processBuilder = new ProcessBuilder("java", "ExercicisMultiproces3_Missatges");
        Process process = processBuilder.start();

        // Configura els streams de comunicació amb el procés fill
        OutputStream outputStream = process.getOutputStream();
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));

        // Llegeix i envia missatges entre pare i fill
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            // Envia missatge al fill
            outputStream.write((line + "\n").getBytes());
            outputStream.flush();

            // Rep i mostra el missatge del fill
            String response = inputStream.readLine();
            if (response != null) {
                System.out.println("Fill: " + response);
            }
        }

        // Tanca els streams
        reader.close();
        inputStream.close();
        outputStream.close();
    }
}
