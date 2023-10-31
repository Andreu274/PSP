package Exercici3;

import java.io.*;

public class Fill {
    public static void main(String[] args) throws IOException {
        // Streams per llegir i escriure dades entre el pare i el fill
        BufferedReader fillReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter fillWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String pareMissatge, fillMissatge;
        while (true) {
            // Llegir missatge del pare i mostrar-lo
            pareMissatge = fillReader.readLine();
            System.out.println("Pare: " + pareMissatge);

            // Enviar resposta al pare
            fillMissatge = "Salutacions de part del fill";
            fillWriter.write(fillMissatge + "\n");
            fillWriter.flush();
        }
    }
}
