import java.io.*;

public class ExecutarFind {
    public static void main(String[] args) {
        File outputFile = new File("sortida.txt");

        if (!outputFile.exists()) {
            System.out.println("El fitxer sortida.txt no existeix.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            String line;
            StringBuilder content = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            String searchText = args[0];
            if (content.toString().toLowerCase().contains(searchText.toLowerCase())) {
                System.out.println("Text trobat a sortida.txt");
            } else {
                System.out.println("Text no trobat a sortida.txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

