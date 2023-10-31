package ExercicisMultiproces3_Missatges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ExercicisMultiproces3_Missatges {
    public static void main(String[] args) throws IOException {
        // Configura els streams de comunicació amb el pare
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        OutputStream outputStream = System.out;

        String message;
        while ((message = inputStream.readLine()) != null) {
            // Mostra el missatge rebut del pare
            System.out.println("Pare: rep missatge del fill \"" + message + "\"");

            // Envia un missatge al pare
            outputStream.write(("Fill: rep missatge del pare \"Salutacions de part del fill\"\n").getBytes());
            outputStream.flush();
        }

        // Tanca els streams
        inputStream.close();
        outputStream.close();
    }
}
