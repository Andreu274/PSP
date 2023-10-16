package Exercici2;

import java.io.*;

public class ExercicisMultiproces2_ModificarString
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out, true);

        try (reader)
        {
            String inputLine;
            while ((inputLine = reader.readLine()) != null)
            {

                // Transformar el texto a mayusculas y substituir las vocales por guiones bajos
                String modifiedText = inputLine.toUpperCase().replaceAll("[AEIOU]", "_");

                // Imprimir la respuesta
                System.out.println("El Fill diu: " + modifiedText);

                // Enviar la respuesta al proceso padre
                writer.println(modifiedText);
            }
        }
    }
}