package Exercici2;

import java.io.*;

public class ExercicisMultiproces2
{
    final static String[] command = {
            "java",
            "src/exercici2/ExercicisMultiproces2_ModificarString.java"
    };

    public static void main(String[] args) throws IOException
    {
        // Crear el proceso hijo
        ProcessBuilder builder = new ProcessBuilder(command);
        Process childProcess = builder.start();

        // Obtener los streams de entrada y salida del proceso hijo
        OutputStream childOutputStream = childProcess.getOutputStream();
        InputStream childInputStream = childProcess.getInputStream();

        // Crear los lectores y escritores de la entrada y salida del proceso padre
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(childOutputStream, true);
        BufferedReader childReader = new BufferedReader(new InputStreamReader(childInputStream));

        String inputLine;

        try {
            while (true)
            {
                System.out.println("Introdueix una frase (exit per sortir): ");
                inputLine = reader.readLine();

                if ("exit".equals(inputLine))
                    break;

                writer.println(inputLine);

                String childOutput = childReader.readLine();
                System.out.println("El PARE diu: " + childOutput);

                // Leer y descartar la respuesta adicional del proceso hijo
                // Tenia un problema que se me acumulaba la respuesta anterior y con esto se ha solucionado
                childReader.readLine();
            }
        } catch (EOFException eofException) {
            eofException.getStackTrace();
        }

        // Cerrar los recursos
        writer.close();
        childReader.close();
        childOutputStream.close();
        childInputStream.close();
    }
}
