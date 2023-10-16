package ExercicisMultiproces2_ModificarString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExercicisMultiproces2_ModificarString {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            // Transforma a majúscules i substitueix les vocals per "_"
            String modifiedLine = line.toUpperCase().replaceAll("[AEIOU]", "_");
            // Envia la línia modificada al pare
            System.out.println("El Fill diu: " + modifiedLine);
        }
        reader.close();
    }
}
