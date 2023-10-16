package Exercici3;

import java.io.*;

public class Pare {
    public static void main(String[] args) throws IOException {
        ProcessBuilder builder = new ProcessBuilder("java", "Fill");
        builder.redirectErrorStream(true);
        Process process = builder.start();

        // Streams per llegir i escriure dades entre el pare i el fill
        BufferedReader pareReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter pareWriter = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        BufferedReader fillReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String pareMissatge, fillMissatge;
        while (true) {
            // Llegir missatge del pare i enviar-lo al fill
            pareMissatge = pareReader.readLine();
            pareWriter.write(pareMissatge + "\n");
            pareWriter.flush();

            // Llegir la resposta del fill i mostrar-la
            fillMissatge = fillReader.readLine();
            System.out.println("Fill: " + fillMissatge);
        }
    }
}
